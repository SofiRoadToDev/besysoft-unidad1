package com.besysoft.practica.services.implementations;

import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.entities.Personaje;
import com.besysoft.practica.repositories.database.PeliculaRepositoryDB;
import com.besysoft.practica.repositories.database.PersonajeRepositoryDB;
import com.besysoft.practica.services.interfaces.PersonajeService;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    private final Validators validators;

    private final PersonajeRepositoryDB personajeRepository;

    private final PeliculaRepositoryDB peliculaRepositoryDB;

    public PersonajeServiceImpl(Validators validators, PersonajeRepositoryDB personajeRepository
            , PeliculaRepositoryDB peliculaRepositoryDB) {
        this.validators = validators;
        this.personajeRepository = personajeRepository;
        this.peliculaRepositoryDB = peliculaRepositoryDB;
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
        if(validators.isPersonajeAlreadyStored(personaje.getNombre())){
            throw  new Exception("Ese personaje ya existe");
        }

        List<Pelicula> peliculasAsociadas=new ArrayList<>();
        personaje.getPeliculasAsociadas().forEach(p->{
            Optional<Pelicula> pelicula=peliculaRepositoryDB.findById(p.getId());
            if(pelicula.isPresent()){
                peliculasAsociadas.add(p);
            }else{
                peliculasAsociadas.add(peliculaRepositoryDB.save(p));
            }
        });
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
    public Personaje actualizaPersonaje(Personaje personaje,Long id) throws Exception {
        if(validators.isPersonajeAlreadyStored(id)){
            Personaje personajeStored=personajeRepository.findById(personaje.getId()).get();
            personaje.getPeliculasAsociadas().forEach(p->{
                Optional<Pelicula>pelicula=peliculaRepositoryDB.findByTitulo(p.getTitulo());
                if(pelicula.isPresent()){
                    personajeStored.getPeliculasAsociadas().add(pelicula.get());
                }else{
                    personajeStored.getPeliculasAsociadas().add(peliculaRepositoryDB.save(p));
                }
            });
            return personajeRepository.save(personajeStored);
        }else{
            throw new Exception("No existe un personaje con ese id");
        }
    }

    @Override
    public void borrarPersonaje(Long id) {
        personajeRepository.deleteById(id);
    }
}
