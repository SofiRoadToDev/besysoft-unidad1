package com.besysoft.practica.services.implementations;

import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.repositories.interfaces.PeliculaRepository;
import com.besysoft.practica.repositories.interfaces.PersonajeRepository;
import com.besysoft.practica.services.interfaces.PersonajeService;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    private final Validators validators;

    private final PersonajeRepository personajeRepository;

    private final PeliculaRepository peliculaRepository;

    public PersonajeServiceImpl(Validators validators, PersonajeRepository personajeRepository, PeliculaRepository peliculaRepository) {
        this.validators = validators;
        this.personajeRepository = personajeRepository;
        this.peliculaRepository = peliculaRepository;
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
            Personaje personajeStored=personajeRepository.getById(id).get();
            personajeStored.setEdad(personaje.getEdad());
            personajeStored.setPeso(personaje.getPeso());
            personajeStored.setNombre(personaje.getNombre());
            personajeStored.setHistoria(personaje.getHistoria());

            personaje.getPeliculasAsociadas().forEach(p->{
                Optional<Pelicula>storedFilm=peliculaRepository.getById(p.getIdPelicula());
                if(storedFilm.isPresent()){
                    personajeStored.getPeliculasAsociadas().add(p);
                }else{
                    personajeStored.getPeliculasAsociadas().add(peliculaRepository.createPelicula(p));
                }
            });
            return personajeRepository.updatePersonaje(personajeStored,id);
        }else{
            throw new Exception("No existe un personaje con ese id");
        }
    }
}
