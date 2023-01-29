package com.besysoft.practica.utilidades;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.dominio.Personaje;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SampleDataGenerator {

    private static  List<Personaje> personajesSample;
    private  static List<Pelicula>peliculasSample;
    private  static  List<Genero>generosSample;

    static Logger logger= Logger.getLogger(SampleDataGenerator.class.getName());



    /*Personajes*/
    private static Personaje capitanAmerica;
    private static Personaje mulan;
    private static Personaje shrek;
    private static Personaje fiona;
    private static Personaje burro;

    /*Peliculas*/
    private static Pelicula elPrimerVengador;
    private static Pelicula soldadoDeInvierno;
    private static Pelicula civilWar;
    private static Pelicula shrekFilm;
    private static Pelicula shrekAfeter;
    private static Pelicula mulanFilm;
    private static Pelicula mulan1;
    /*Generos*/
    private static Genero infantil;
    private static Genero aventura;
    private static Genero heroes;






    private SampleDataGenerator(){
        capitanAmerica= new Personaje("Capitan America",33,80,"Lo congelaron por años y después se unió a los vengadores");
        mulan= new Personaje("Mulan",15,50," Fue a la guerra a pelear en lugar de su padre disfrazada de hombre");
        shrek= new Personaje("Shrek",40,250,"Es un ogro gordo y feo pero de buen corazón que se casa con una princesa humana convertida en ogro");
        fiona=new Personaje("Fiona",25,80," Es una princesa que se enamoró de Shrek y se volvió ogro para que él no se sienta mal");
        burro=new Personaje("Burro",5,60," Es un amigo de shrek que se casó con una dragona y tuvo muchos hijos");

        elPrimerVengador=new Pelicula("El primer vengador", LocalDate.of(2011,11,2),8);
        soldadoDeInvierno=new Pelicula("Soldado de invierno", LocalDate.of(2013,10,12),5);
        civilWar=new Pelicula("Civil War",LocalDate.of(2015,8,23),9);
        shrekFilm=new Pelicula("Shrek1", LocalDate.of(2001,3,12),7);
        shrekAfeter=new Pelicula("Shrek Forever After", LocalDate.of(2010,12,1),9);
        mulanFilm= new Pelicula("Mulan live action",LocalDate.of(2020,07,4),10);
        mulan1= new Pelicula("Mulan Disney", LocalDate.of(1998,10,2),10);

        heroes= new Genero("superheroes");
        infantil= new Genero("infantil");
        aventura= new Genero("aventura");


        civilWar.getPersonajesAsociados().add(capitanAmerica);
        shrekFilm.getPersonajesAsociados().add(shrek);
        shrekFilm.getPersonajesAsociados().add(burro);
        shrekFilm.getPersonajesAsociados().add(fiona);

        shrekAfeter.getPersonajesAsociados().add(shrek);
        shrekAfeter.getPersonajesAsociados().add(burro);
        shrekAfeter.getPersonajesAsociados().add(fiona);

        mulanFilm.getPersonajesAsociados().add(mulan);

        mulan1.getPersonajesAsociados().add(mulan);

        civilWar.getPersonajesAsociados().add(capitanAmerica);

        elPrimerVengador.getPersonajesAsociados().add(capitanAmerica);

        soldadoDeInvierno.getPersonajesAsociados().add(capitanAmerica);


        civilWar.setGenero(heroes);
        shrekFilm.setGenero(aventura);
        shrekAfeter.setGenero(aventura);
        mulan1.setGenero(infantil);
        mulanFilm.setGenero(infantil);
        elPrimerVengador.setGenero(heroes);
        soldadoDeInvierno.setGenero(heroes);

        mulan.getPeliculasAsociadas().add(mulanFilm);
        mulan.getPeliculasAsociadas().add(mulan1);

        capitanAmerica.getPeliculasAsociadas().add(soldadoDeInvierno);
        capitanAmerica.getPeliculasAsociadas().add(elPrimerVengador);
        capitanAmerica.getPeliculasAsociadas().add(civilWar);
        shrek.getPeliculasAsociadas().add(shrekAfeter);
        shrek.getPeliculasAsociadas().add(shrekFilm);
        fiona.getPeliculasAsociadas().add(shrekAfeter);
        fiona.getPeliculasAsociadas().add(shrekFilm);
        burro.getPeliculasAsociadas().add(shrekAfeter);
        burro.getPeliculasAsociadas().add(shrekFilm);


           }


    public static List<Genero> getGenerosSample() {


        heroes.getPeliculasDelGenero().add(civilWar);
        heroes.getPeliculasDelGenero().add(elPrimerVengador);
        heroes.getPeliculasDelGenero().add(soldadoDeInvierno);

        infantil.getPeliculasDelGenero().add(mulan1);
        infantil.getPeliculasDelGenero().add(mulanFilm);

       aventura.getPeliculasDelGenero().add(shrekFilm);
       aventura.getPeliculasDelGenero().add(shrekAfeter);

        generosSample=new ArrayList<>();
        generosSample.add(infantil);
        generosSample.add(aventura);
        generosSample.add(heroes);

        return generosSample;
    }

    public static List<Pelicula> getPeliculasSample() {

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

    public static List<Personaje>getPersonajesSample(){

        personajesSample=new ArrayList<>();

        personajesSample.add(capitanAmerica);
        personajesSample.add(mulan);
        personajesSample.add(shrek);
        personajesSample.add(fiona);
        personajesSample.add(burro);

        return personajesSample;
    }






}
