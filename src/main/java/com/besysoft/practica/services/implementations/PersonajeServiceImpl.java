package com.besysoft.practica.services.implementations;

import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.repositories.interfaces.PersonajeRepository;
import com.besysoft.practica.services.interfaces.PersonajeService;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    private final Validators validators;

    private final PersonajeRepository personajeRepository;

    public PersonajeServiceImpl(Validators validators, PersonajeRepository personajeRepository) {
        this.validators = validators;
        this.personajeRepository = personajeRepository;
    }

    @Override
    public Iterable<Personaje> obtenerTodos() {
        return personajeRepository.getAllFromSampleData();
    }

    @Override
    public Optional<Personaje> buscarPorNombre(String nombre) throws Exception {
        boolean isOnlyLetters=nombre.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        Optional<Personaje>p;
        if(isOnlyLetters){
               p=personajeRepository.getByName(nombre);
            if(p.isPresent()){
                return p;
            }else{
                throw new Exception("personaje no encontrado");
            }

        }else{
            throw new Exception("El nombre solo puede contener letras");
        }

    }

    @Override
    public Personaje crearPersonaje(Personaje personaje) throws Exception {
        return personajeRepository.createPersonaje(personaje);
    }

    @Override
    public Iterable<Personaje> buscarPorRangoEdad(int desde, int hasta) throws Exception {

        if((desde>0 && hasta>0)&& desde<=hasta && hasta <20000){
           return  personajeRepository.getByAgeRange(desde,hasta);
        }else{
            throw new Exception("Coloque rango de edad válido números enteros positivos y desde debe ser menor o igual que hasta. La edad máxima de los personajes es 20000 años");
        }
    }

    @Override
    public Personaje actualizaPersonaje(Personaje personaje, int id) throws Exception {
        if(Validators.isPersonajeAlreadyStored(id)){
            return personajeRepository.updatePersonaje(personaje,id);
        }else{
            throw new Exception("No existe un personaje con ese id");
        }
    }
}
