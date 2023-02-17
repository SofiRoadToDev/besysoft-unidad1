package com.besysoft.practica.repositories.memory.implementations;

import com.besysoft.practica.dominio.GeneroMem;
import com.besysoft.practica.repositories.memory.interfaces.GeneroRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GeneroRepositoryInMemoryImpl implements GeneroRepository {


    private List<GeneroMem> generoMems =new ArrayList<>();

    private GeneroMem infantil;
    private GeneroMem aventura;
    private GeneroMem heroes;

    public GeneroRepositoryInMemoryImpl(){
        System.out.println("creando instancia de genero GenroRepImpl");

        heroes= new GeneroMem("superheroes");
        infantil= new GeneroMem("infantil");
        aventura= new GeneroMem("aventura");

        generoMems.add(heroes);
        generoMems.add(infantil);
        generoMems.add(aventura);
    }
    @Override
    public GeneroMem createGenero(GeneroMem generoMem)  {
        generoMem.setIdGenero(GeneroMem.getIdCounter()+1);
        this.generoMems.add(generoMem);
        return generoMem;
    }

    @Override
    public Iterable<GeneroMem> getAll() {
        return this.generoMems;
    }

    @Override
    public Optional<GeneroMem> getById(int id)  {
        return  generoMems
                .stream().filter(g->g.getIdGenero()==id).findAny();
    }

    @Override
    public Optional<GeneroMem> getByNombre(String nombre)  {
        return generoMems
                .stream().filter(g->g.getNombre().toLowerCase().equals(nombre.toLowerCase()))
                .findAny();
    }

    @Override
    public GeneroMem updateGenero(GeneroMem generoMem, int id)  {
        generoMems.forEach(g->{
            if(g.getIdGenero()==id){
                int i= generoMems.indexOf(g);
                generoMem.setIdGenero(g.getIdGenero());
                generoMems.set(i, generoMem);
            }
        });
        return generoMem;
    }
}
