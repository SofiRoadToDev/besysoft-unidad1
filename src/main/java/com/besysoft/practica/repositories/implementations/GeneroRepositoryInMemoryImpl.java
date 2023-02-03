package com.besysoft.practica.repositories.implementations;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.repositories.interfaces.GeneroRepository;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class GeneroRepositoryInMemoryImpl implements GeneroRepository {


    private List<Genero>generos=new ArrayList<>();

    private  Genero infantil;
    private  Genero aventura;
    private  Genero heroes;

    public GeneroRepositoryInMemoryImpl(){
        System.out.println("creando instancia de genero GenroRepImpl");

        heroes= new Genero("superheroes");
        infantil= new Genero("infantil");
        aventura= new Genero("aventura");

        generos.add(heroes);
        generos.add(infantil);
        generos.add(aventura);
    }
    @Override
    public Genero createGenero(Genero genero)  {
        genero.setIdGenero(Genero.getIdCounter()+1);
        this.generos.add(genero);
        return genero;
    }

    @Override
    public Iterable<Genero> getAll() {
        return this.generos;
    }

    @Override
    public Optional<Genero> getById(int id)  {
        return  generos
                .stream().filter(g->g.getIdGenero()==id).findAny();
    }

    @Override
    public Optional<Genero> getByNombre(String nombre)  {
        return generos
                .stream().filter(g->g.getNombre().toLowerCase().equals(nombre.toLowerCase()))
                .findAny();
    }

    @Override
    public Genero updateGenero(Genero genero, int id)  {
        generos.forEach(g->{
            if(g.getIdGenero()==id){
                int i=generos.indexOf(g);
                genero.setIdGenero(g.getIdGenero());
                generos.set(i,genero);
            }
        });
        return genero;
    }
}
