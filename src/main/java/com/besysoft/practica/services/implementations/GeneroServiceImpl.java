package com.besysoft.practica.services.implementations;

import com.besysoft.practica.entities.Genero;
import com.besysoft.practica.repositories.database.GeneroRepositoryDB;
import com.besysoft.practica.services.interfaces.GeneroService;
import com.besysoft.practica.utilidades.Validators;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class GeneroServiceImpl implements GeneroService {

    private final Validators validators;

    private final GeneroRepositoryDB repository;

    @Override
    public Iterable<Genero> obtenerTodos() throws Exception {
        return this.repository.findAll();
    }

    @Override
    public Optional<Genero> buscarPorId(Long id) throws Exception {
        return repository.findById(id);
    }

    @Override
    public Optional<Genero> buscarPorNombre(String nombre) throws Exception {
        return repository.findByNombre(nombre);
    }

    @Override
    public Genero crearGenero(Genero genero) throws Exception {

        boolean isOnlyLetters= genero.getNombre().matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        if(!isOnlyLetters){
            throw new Exception("El genero solo debe tener letras");
        }else{
            if(validators.isGeneroAlreadyStored(genero.getNombre())){
                throw new Exception("Ese género ya existe");
            }else{
                return repository.save(genero);
            }

        }

    }

    @Override
    public Genero actualizarGenero(Genero genero, Long id) throws Exception {
        boolean isOnlyLetters= genero.getNombre().matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        if(!isOnlyLetters){
            log.info(isOnlyLetters);
            throw new Exception("El nombre del genero solo debe tener letras");
        }else{
            Optional<Genero> gen=repository.findById(id);
            if(gen.isPresent()){
               return repository.save(genero);
            }else{
                throw new Exception("El id no corresponde a ningún género existente");
            }

        }

    }
        @Override
        public void borrarGenero(Long id)  {
        repository.deleteById(id);
        }
}
