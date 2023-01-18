package com.besysoft.practica.utilidades;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.dominio.Personaje;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SampleDataGenerator {

    private   List<Personaje> personajesSample;
    private   List<Pelicula>peliculasSample;
    private   List<Genero>generosSample;



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

        heroes= new Genero("Super Heroes");
        infantil= new Genero("Infantil");
        aventura= new Genero("Aventura");

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

        civilWar.setPersonajesAsociados(List.of(capitanAmerica));
        shrekFilm.setPersonajesAsociados(List.of(shrek,burro,fiona));
        shrekAfeter.setPersonajesAsociados(List.of(shrek,burro,fiona));
        mulanFilm.setPersonajesAsociados(List.of(mulan));

        civilWar.setGenero(heroes);
        shrekFilm.setGenero(aventura);
        shrekAfeter.setGenero(aventura);
        mulanFilm.setGenero(infantil);

        peliculasSample.add(elPrimerVengador);
        peliculasSample.add(soldadoDeInvierno);
        peliculasSample.add(civilWar);
        peliculasSample.add(shrekAfeter);
        peliculasSample.add(shrekFilm);
        peliculasSample.add(mulanFilm);
        peliculasSample.add(mulan1);

        System.out.println("tamaño del arreglo de peliculas "+peliculasSample.size());

        return peliculasSample;
    }

    public List<Personaje>getPersonajesSample(){
       /* iniciarDatos();*/


        personajesSample=new ArrayList<>();
        mulan.setPeliculasAsociadas(List.of(mulan1,mulanFilm));
        capitanAmerica.setPeliculasAsociadas(List.of(soldadoDeInvierno,elPrimerVengador,civilWar));
        shrek.setPeliculasAsociadas(List.of(shrekFilm,shrekAfeter));
        fiona.setPeliculasAsociadas(List.of(shrekFilm,shrekAfeter));
        burro.setPeliculasAsociadas(List.of(shrekFilm,shrekAfeter));

        personajesSample.add(capitanAmerica);
        personajesSample.add(mulan);
        personajesSample.add(shrek);
        personajesSample.add(fiona);
        personajesSample.add(burro);

        return personajesSample;
    }
}
