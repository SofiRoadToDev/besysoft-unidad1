package com.besysoft.practica.utilidades;

import com.besysoft.practica.dominio.GeneroMem;
import com.besysoft.practica.dominio.PeliculaMem;
import com.besysoft.practica.dominio.PersonajeMem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Deprecated
public class SampleDataGenerator {

    private static  List<PersonajeMem> personajesSample;
    private  static List<PeliculaMem>peliculasSample;
    private  static  List<GeneroMem>generosSample;

    static Logger logger= Logger.getLogger(SampleDataGenerator.class.getName());



    /*Personajes*/
    private static PersonajeMem capitanAmerica;
    private static PersonajeMem mulan;
    private static PersonajeMem shrek;
    private static PersonajeMem fiona;
    private static PersonajeMem burro;

    /*Peliculas*/
    private static PeliculaMem elPrimerVengador;
    private static PeliculaMem soldadoDeInvierno;
    private static PeliculaMem civilWar;
    private static PeliculaMem shrekFilm;
    private static PeliculaMem shrekAfeter;
    private static PeliculaMem mulanFilm;
    private static PeliculaMem mulan1;
    /*Generos*/
    private static GeneroMem infantil;
    private static GeneroMem aventura;
    private static GeneroMem heroes;

    private static boolean isInitialized=false;






    public static List<GeneroMem> getGenerosSample() {
        if(isInitialized==false){
            initializeData();
        }
        return generosSample;
    }

    public static List<PeliculaMem> getPeliculasSample() {
        if(isInitialized==false){
            initializeData();
        }
        return peliculasSample;
    }



    public static List<PersonajeMem>getPersonajesSample(){
            if(isInitialized==false){
                initializeData();
            }
        return personajesSample;
    }




    private static void initializeData(){
        capitanAmerica= new PersonajeMem("Capitan America",33,80,"Lo congelaron por años y después se unió a los vengadores");
        mulan= new PersonajeMem("Mulan",15,50," Fue a la guerra a pelear en lugar de su padre disfrazada de hombre");
        shrek= new PersonajeMem("Shrek",40,250,"Es un ogro gordo y feo pero de buen corazón que se casa con una princesa humana convertida en ogro");
        fiona=new PersonajeMem("Fiona",25,80," Es una princesa que se enamoró de Shrek y se volvió ogro para que él no se sienta mal");
        burro=new PersonajeMem("Burro",5,60," Es un amigo de shrek que se casó con una dragona y tuvo muchos hijos");

        elPrimerVengador=new PeliculaMem("El primer vengador", LocalDate.of(2011,11,2),8);
        soldadoDeInvierno=new PeliculaMem("Soldado de invierno", LocalDate.of(2013,10,12),5);
        civilWar=new PeliculaMem("Civil War",LocalDate.of(2015,8,23),9);
        shrekFilm=new PeliculaMem("Shrek1", LocalDate.of(2001,3,12),7);
        shrekAfeter=new PeliculaMem("Shrek Forever After", LocalDate.of(2010,12,1),9);
        mulanFilm= new PeliculaMem("Mulan live action",LocalDate.of(2020,07,4),10);
        mulan1= new PeliculaMem("Mulan Disney", LocalDate.of(1998,10,2),10);

        heroes= new GeneroMem("superheroes");
        infantil= new GeneroMem("infantil");
        aventura= new GeneroMem("aventura");



        shrekFilm.getPersonajesAsociados().add(shrek);
        shrekFilm.getPersonajesAsociados().add(burro);
        shrekFilm.getPersonajesAsociados().add(fiona);

        shrekAfeter.getPersonajesAsociados().add(shrek);
        shrekAfeter.getPersonajesAsociados().add(burro);
        shrekAfeter.getPersonajesAsociados().add(fiona);

        mulanFilm.getPersonajesAsociados().add(mulan);

        mulan1.getPersonajesAsociados().add(mulan);


        elPrimerVengador.getPersonajesAsociados().add(capitanAmerica);
        civilWar.getPersonajesAsociados().add(capitanAmerica);
        soldadoDeInvierno.getPersonajesAsociados().add(capitanAmerica);


        civilWar.setGeneroMem(heroes);
        shrekFilm.setGeneroMem(aventura);
        shrekAfeter.setGeneroMem(aventura);
        mulan1.setGeneroMem(infantil);
        mulanFilm.setGeneroMem(infantil);
        elPrimerVengador.setGeneroMem(heroes);
        soldadoDeInvierno.setGeneroMem(heroes);

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

        peliculasSample=new ArrayList<>();

        peliculasSample.add(elPrimerVengador);
        peliculasSample.add(soldadoDeInvierno);
        peliculasSample.add(civilWar);
        peliculasSample.add(shrekAfeter);
        peliculasSample.add(shrekFilm);
        peliculasSample.add(mulanFilm);
        peliculasSample.add(mulan1);

        personajesSample=new ArrayList<>();

        personajesSample.add(capitanAmerica);
        personajesSample.add(mulan);
        personajesSample.add(shrek);
        personajesSample.add(fiona);
        personajesSample.add(burro);


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

        isInitialized=true;
    }





}
