package com.besysoft.practica.utilidades;

import com.besysoft.practica.controllers.PeliculasController;
import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.dominio.Personaje;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SampleDataGenerator {

    private   List<Personaje> personajesSample;
    private   List<Pelicula>peliculasSample;
    private   List<Genero>generosSample;

    static Logger logger= Logger.getLogger(SampleDataGenerator.class.getName());



    /*Personajes*/
    Personaje capitanAmerica;
    Personaje mulan;
    Personaje shrek;
    Personaje fiona;
    Personaje burro;

    /*Peliculas*/
    Pelicula elPrimerVengador;
    Pelicula soldadoDeInvierno;
    Pelicula civilWar;
    Pelicula shrekFilm;
    Pelicula shrekAfeter;
    Pelicula mulanFilm;
    Pelicula mulan1;
    /*Generos*/
    Genero infantil;
    Genero aventura;
    Genero heroes;






    public SampleDataGenerator(){
        this.capitanAmerica= new Personaje("Capitan America",33,80,"Lo congelaron por años y después se unió a los vengadores");
        this.mulan= new Personaje("Mulan",15,50," Fue a la guerra a pelear en lugar de su padre disfrazada de hombre");
        this.shrek= new Personaje("Shrek",40,250,"Es un ogro gordo y feo pero de buen corazón que se casa con una princesa humana convertida en ogro");
        this.fiona=new Personaje("Fiona",25,80," Es una princesa que se enamoró de Shrek y se volvió ogro para que él no se sienta mal");
        this.burro=new Personaje("Burro",5,60," Es un amigo de shrek que se casó con una dragona y tuvo muchos hijos");

        this.elPrimerVengador=new Pelicula("El primer vengador", LocalDate.of(2011,11,2),8);
        this.soldadoDeInvierno=new Pelicula("Soldado de invierno", LocalDate.of(2013,10,12),5);
        this.civilWar=new Pelicula("Civil War",LocalDate.of(2015,8,23),9);
        this.shrekFilm=new Pelicula("Shrek1", LocalDate.of(2001,3,12),7);
        this.shrekAfeter=new Pelicula("Shrek Forever After", LocalDate.of(2010,12,1),9);
        this.mulanFilm= new Pelicula("Mulan live action",LocalDate.of(2020,07,4),10);
        this.mulan1= new Pelicula("Mulan Disney", LocalDate.of(1998,10,2),10);

        this.heroes= new Genero("superheroes");
        this.infantil= new Genero("infantil");
        this.aventura= new Genero("aventura");

        this.civilWar.setPersonajesAsociados(List.of(capitanAmerica));
        this.shrekFilm.setPersonajesAsociados(List.of(shrek,burro,fiona));
        this.shrekAfeter.setPersonajesAsociados(List.of(shrek,burro,fiona));
        this.mulanFilm.setPersonajesAsociados(List.of(mulan));
        this.civilWar.setPersonajesAsociados(List.of(capitanAmerica));

        this.civilWar.setGenero(heroes);
        this.shrekFilm.setGenero(aventura);
        this.shrekAfeter.setGenero(aventura);
        this.mulan1.setGenero(infantil);
        this.mulanFilm.setGenero(infantil);
        this.elPrimerVengador.setGenero(heroes);
        this.soldadoDeInvierno.setGenero(heroes);

        this.mulan.setPeliculasAsociadas(List.of(mulan1,mulanFilm));
        this.capitanAmerica.setPeliculasAsociadas(List.of(soldadoDeInvierno,elPrimerVengador,civilWar));
        this.shrek.setPeliculasAsociadas(List.of(shrekFilm,shrekAfeter));
        this.fiona.setPeliculasAsociadas(List.of(shrekFilm,shrekAfeter));
        this.burro.setPeliculasAsociadas(List.of(shrekFilm,shrekAfeter));



           }








    public List<Genero> getGenerosSample() {

        heroes.setPeliculasDelGenero(List.of(civilWar,elPrimerVengador,soldadoDeInvierno));
        infantil.setPeliculasDelGenero(List.of(mulanFilm,mulan1));
        aventura.setPeliculasDelGenero(List.of(shrekFilm,shrekAfeter));

        generosSample=new ArrayList<>();
        generosSample.add(infantil);
        generosSample.add(aventura);
        generosSample.add(heroes);



        return generosSample;
    }

    public List<Pelicula> getPeliculasSample() {

        peliculasSample=new ArrayList<>();



        peliculasSample.add(elPrimerVengador);
        peliculasSample.add(soldadoDeInvierno);
        peliculasSample.add(civilWar);
        peliculasSample.add(shrekAfeter);
        peliculasSample.add(shrekFilm);
        peliculasSample.add(mulanFilm);
        peliculasSample.add(mulan1);



        return peliculasSample;
    }

    public List<Personaje>getPersonajesSample(){
       /* iniciarDatos();*/


        personajesSample=new ArrayList<>();


        personajesSample.add(capitanAmerica);
        personajesSample.add(mulan);
        personajesSample.add(shrek);
        personajesSample.add(fiona);
        personajesSample.add(burro);

        return personajesSample;
    }
}
