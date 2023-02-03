package com.besysoft.practica.repositories.implementations;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.repositories.interfaces.GeneroRepository;
import com.besysoft.practica.utilidades.SampleDataGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeneroRepositoryInMemoryImpl implements GeneroRepository {


    private List<Genero>generos=new ArrayList<>();

    private  Genero infantil;
    private  Genero aventura;
    private  Genero heroes;

    public GeneroRepositoryInMemoryImpl(){
        System.out.println("creando instancia de genero");

        heroes= new Genero("superheroes");
        infantil= new Genero("infantil");
        aventura= new Genero("aventura");
    }
    @Override
    public void createGenero(Genero genero) throws Exception {
        genero.setIdGenero(Genero.getIdCounter()+1);
        generos.add(genero);
    }

    @Override
    public Iterable<Genero> getAll() {
        return generos;
    }

    @Override
    public Optional<Genero> getById(int id) throws Exception {
        return  generos
                .stream().filter(g->g.getIdGenero()==id).findAny();
    }

    @Override
    public Optional<Genero> getByNombre(String nombre) throws Exception {
        return generos
                .stream().filter(g->g.getNombre().toLowerCase().equals(nombre.toLowerCase()))
                .findAny();
    }

    @Override
    public void updateGenero(Genero genero, int id) throws Exception {
        generos.forEach(g->{
            if(g.getIdGenero()==id){
                int i=SampleDataGenerator.getGenerosSample().indexOf(g);
                genero.setIdGenero(g.getIdGenero());
                SampleDataGenerator.getGenerosSample().set(i,genero);
            }
        });

    }
}
