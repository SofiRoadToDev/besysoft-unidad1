package com.besysoft.practica.services.implementations;

import com.besysoft.practica.dominio.PersonajeMem;
import com.besysoft.practica.repositories.memory.interfaces.PersonajeRepository;
import com.besysoft.practica.services.interfaces.PersonajeService;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    private final Validators validators;

    private final PersonajeRepository personajeRepository;

    public PersonajeServiceImpl(Validators validators, PersonajeRepository personajeRepository) {
        this.validators = validators;
        this.personajeRepository = personajeRepository;
    }

    @Override
    public Iterable<PersonajeMem> obtenerTodos() {
        return personajeRepository.getAllFromSampleData();
    }

    @Override
    public Optional<PersonajeMem> buscarPorNombre(String nombre) throws Exception {
        boolean isOnlyLetters=nombre.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        Optional<PersonajeMem>p;
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
    public PersonajeMem crearPersonaje(PersonajeMem personajeMem) throws Exception {
        return personajeRepository.createPersonaje(personajeMem);
    }

    @Override
    public Iterable<PersonajeMem> buscarPorRangoEdad(int desde, int hasta) throws Exception {

        if((desde>0 && hasta>0)&& desde<=hasta && hasta <20000){
           return  personajeRepository.getByAgeRange(desde,hasta);
        }else{
            throw new Exception("Coloque rango de edad válido números enteros positivos y desde debe ser menor o igual que hasta. La edad máxima de los personajes es 20000 años");
        }
    }

    @Override
    public PersonajeMem actualizaPersonaje(PersonajeMem personajeMem, int id) throws Exception {
        if(Validators.isPersonajeAlreadyStored(id)){
            return personajeRepository.updatePersonaje(personajeMem,id);
        }else{
            throw new Exception("No existe un personaje con ese id");
        }
    }
}
