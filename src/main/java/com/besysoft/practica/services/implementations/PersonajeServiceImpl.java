package com.besysoft.practica.services.implementations;

import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.entities.Personaje;
import com.besysoft.practica.exceptions.PersonajeDoesntExistsException;
import com.besysoft.practica.exceptions.PersonajeExistsException;
import com.besysoft.practica.repositories.database.PeliculaRepositoryDB;
import com.besysoft.practica.repositories.database.PersonajeRepositoryDB;
import com.besysoft.practica.services.interfaces.PersonajeService;
import com.besysoft.practica.utilidades.Validators;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class PersonajeServiceImpl implements PersonajeService {

    private final Validators validators;

    private final PersonajeRepositoryDB personajeRepository;

    private final PeliculaRepositoryDB peliculaRepositoryDB;


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
                log.info("búsqueda por nombre personaje no encontrado: "+nombre);
                throw new Exception("personaje no encontrado");
            }

        }else{
            log.info("Formato inválido para buscar personaje por nombre: "+nombre);
            throw new Exception("El nombre solo puede contener letras");
        }

    }

    @Override
    public Personaje crearPersonaje(Personaje personaje) throws Exception, PersonajeExistsException {
        if(validators.isPersonajeAlreadyStored(personaje.getNombre())){
            log.info("intento de crear un personaje ya existente: "+personaje.getNombre());
            throw  new PersonajeExistsException("Ese personaje ya existe");
        }

        List<Pelicula> peliculasAsociadas=new ArrayList<>();
        personaje.getPeliculasAsociadas().forEach(p->{
            Optional<Pelicula> pelicula=peliculaRepositoryDB.findByTitulo(p.getTitulo());
            if(pelicula.isPresent()){
                peliculasAsociadas.add(p);
            }else{
                peliculasAsociadas.add(peliculaRepositoryDB.save(p));
            }
        });
        return   personajeRepository.save(personaje);
    }

    @Override
    public Iterable<Personaje> buscarPorRangoEdad(int desde, int hasta) throws Exception {

        if((desde>0 && hasta>0)&& desde<=hasta && hasta <20000){
           return  personajeRepository.findByEdadBetween(desde,hasta);
        }else{
            log.info("Formato inválido en rango de edad del personaje: "+desde+" "+hasta);
            throw new Exception("Coloque rango de edad válido números enteros positivos y desde debe ser menor o igual que hasta. La edad máxima de los personajes es 20000 años");
        }
    }

    @Override
    public Personaje actualizaPersonaje(Personaje personaje,Long id) throws Exception, PersonajeDoesntExistsException {
        if(validators.isPersonajeAlreadyStored(id)){
            Personaje personajeStored=personajeRepository.findById(id).get();
            personaje.getPeliculasAsociadas().forEach(p->{
                Optional<Pelicula>pelicula=peliculaRepositoryDB.findByTitulo(p.getTitulo());
                if(pelicula.isPresent()){
                    personajeStored.getPeliculasAsociadas().add(pelicula.get());
                }else{
                    personajeStored.getPeliculasAsociadas().add(peliculaRepositoryDB.save(p));
                }
            });
            return personajeRepository.save(personaje);
        }else{
            log.info("Intento de actualizar personaje id: "+id+" inexistente");
            throw new PersonajeDoesntExistsException("No existe un personaje con ese id");
        }
    }

    @Override
    public void borrarPersonaje(Long id) {
        log.info("Borrado personaje id: "+id);
        personajeRepository.deleteById(id);
    }
}
