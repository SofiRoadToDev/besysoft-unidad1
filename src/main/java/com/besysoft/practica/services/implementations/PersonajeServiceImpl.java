package com.besysoft.practica.services.implementations;

import com.besysoft.practica.dominio.PersonajeMem;
import com.besysoft.practica.entities.Personaje;
import com.besysoft.practica.repositories.database.PersonajeRepositoryDB;
import com.besysoft.practica.services.interfaces.PersonajeService;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    private final Validators validators;

    private final PersonajeRepositoryDB personajeRepository;

    public PersonajeServiceImpl(Validators validators, PersonajeRepositoryDB personajeRepository) {
        this.validators = validators;
        this.personajeRepository = personajeRepository;
    }

    @Override
    public Iterable<Personaje> obtenerTodos() {
        return personajeRepository.findAll();
    }

    @Override
    public Optional<Personaje> buscarPorNombre(String nombre) throws Exception {
        boolean isOnlyLetters=nombre.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        Optional<Personaje>p;
        if(isOnlyLetters){
               p=personajeRepository.findByNombre(nombre);
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
        return personajeRepository.save(personaje);
    }

    @Override
    public Iterable<Personaje> buscarPorRangoEdad(int desde, int hasta) throws Exception {

        if((desde>0 && hasta>0)&& desde<=hasta && hasta <20000){
           return  personajeRepository.findByEdadBetween(desde,hasta);
        }else{
            throw new Exception("Coloque rango de edad válido números enteros positivos y desde debe ser menor o igual que hasta. La edad máxima de los personajes es 20000 años");
        }
    }

    @Override
    public Personaje actualizaPersonaje(Personaje personaje) throws Exception {
        if(validators.isPersonajeAlreadyStored(personaje.getId())){
            return personajeRepository.save(personaje);
        }else{
            throw new Exception("No existe un personaje con ese id");
        }
    }
}
