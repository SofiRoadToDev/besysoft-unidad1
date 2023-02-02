package com.besysoft.practica.utilidades;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.dominio.Personaje;

import java.time.LocalDate;
import java.util.Optional;

public class Validators {



    public static boolean isDateRight(String date){

        boolean digits=date.matches("^\\d{8}$");

        int day=Integer.parseInt(date.substring(0,2)) ;
        int month=Integer.parseInt(date.substring(2,4));
        int year=Integer.parseInt(date.substring(4,8));
        boolean rightDayValue=day>0 && day<=31;
        boolean rightMonthValue=month>0 && month<=12;
        boolean rigthYearValue=year>1900 && year<= LocalDate.now().getYear();

        return digits && rightDayValue && rightMonthValue && rigthYearValue;
    }

    public static boolean isPeliculaAlreadyStored(String peli){
        Optional<Pelicula> pelicula=SampleDataGenerator
                .getPeliculasSample()
                .stream().filter(p->p.getTitulo().toLowerCase().equals(peli.toLowerCase())).findAny();
        if(pelicula.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isPersonajeAlreadyStored(String per){
        Optional<Personaje> personaje=SampleDataGenerator
                .getPersonajesSample()
                .stream().filter(p->p.getNombre().toLowerCase().equals(per.toLowerCase())).findAny();
        if(personaje.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isPeliculaAlreadyStored(int id){

        Optional<Pelicula> pelicula=SampleDataGenerator
                .getPeliculasSample()
                .stream().filter(p->p.getIdPelicula()==id).findAny();

        if(pelicula.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    // En estos casos para validar también podría usar el trim() para quitar los espacios
    // me parece mejor que los géneros se escriban con el espacio y si no este incorrecto

    public static boolean isGeneroAlreadyStored(String genero){
        boolean isStored=false;
        Optional<Genero>gen=SampleDataGenerator.getGenerosSample()
                .stream().filter(g->g.getNombre().toLowerCase().equals(genero.toLowerCase()))
                .findAny();
        if(gen.isPresent()){
            isStored= true;
        }
        return isStored;
    }

    public static boolean isPersonajeAlreadyStored(int id){

        Optional<Personaje> personaje=SampleDataGenerator
                .getPersonajesSample()
                .stream().filter(p->p.getIdPersonaje()==id).findAny();
        if(personaje.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
