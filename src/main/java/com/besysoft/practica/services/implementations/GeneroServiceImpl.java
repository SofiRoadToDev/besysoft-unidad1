package com.besysoft.practica.services.implementations;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.repositories.interfaces.GeneroRepository;
import com.besysoft.practica.services.interfaces.GeneroService;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeneroServiceImpl implements GeneroService {

    private final Validators validators;

    private final GeneroRepository repository;

    public GeneroServiceImpl(GeneroRepository repository, Validators validators){
        this.repository=repository;
        this.validators=validators;
    }
    @Override
    public Iterable<Genero> obtenerTodos() throws Exception {
        return repository.getAll();
    }

    @Override
    public Optional<Genero> buscarPorId(int id) throws Exception {
        return repository.getById(id);
    }

    @Override
    public Optional<Genero> buscarPorNombre(String nombre) throws Exception {
        return repository.getByNombre(nombre);
    }

    @Override
    public Genero crearGenero(Genero genero) throws Exception {

        boolean isOnlyLetters=genero.getNombre().matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        if(!isOnlyLetters){
            throw new Exception("El genero solo debe tener letras");
        }else{
            if(validators.isGeneroAlreadyStored(genero.getNombre())){
                throw new Exception("Ese género ya existe");
            }else{
                return repository.createGenero(genero);
            }

        }

    }

    @Override
    public Genero actualizarGenero(Genero genero, int id) throws Exception {
        boolean isOnlyLetters=genero.getNombre().matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        if(!isOnlyLetters){
            throw new Exception("El nombre del genero solo debe tener letras");
        }else{
            Optional<Genero> gen=SampleDataGenerator.getGenerosSample()
                    .stream()
                    .filter(g->g.getIdGenero()==id)
                    .findAny();
            if(gen.isPresent()){
               return repository.updateGenero(genero,id);
            }else{
                throw new Exception("El id no corresponde a ningún género existente");
            }

        }

    }
}
