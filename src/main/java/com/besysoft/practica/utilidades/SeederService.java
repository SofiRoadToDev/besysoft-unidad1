package com.besysoft.practica.utilidades;

import com.besysoft.practica.entities.Genero;
import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.entities.Personaje;
import com.besysoft.practica.repositories.database.GeneroRepositoryDB;
import com.besysoft.practica.repositories.database.PeliculaRepositoryDB;
import com.besysoft.practica.repositories.database.PersonajeRepositoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class SeederService {

    @Autowired
    PeliculaRepositoryDB peliculaRepositoryDB;

    @Autowired
    PersonajeRepositoryDB personajeRepositoryDB;

    @Autowired
    GeneroRepositoryDB generoRepositoryDB;


    @EventListener
    public void seed(ContextRefreshedEvent event){
        Pelicula mulan= new Pelicula();
        mulan.setCalificacion(8);
        mulan.setFechaCreacion(Date.valueOf("2012-07-04"));
        mulan.setTitulo("Mulan I");

        peliculaRepositoryDB.save(mulan);


        Personaje capitan=new Personaje();
        capitan.setEdad(33);
        capitan.setPeso(90);
        capitan.setNombre("Capitan America");
        capitan.setHistoria("Fue congelado durante años y luego se unio a los vengadores");
        personajeRepositoryDB.save(capitan);


        generoRepositoryDB.save(new Genero("aventura"));
        generoRepositoryDB.save(new Genero("animacion"));
        generoRepositoryDB.save(new Genero("superheroes"));
    }

}
