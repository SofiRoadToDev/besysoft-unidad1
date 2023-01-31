package com.besysoft.practica.utilidades;

import com.besysoft.practica.dominio.Pelicula;

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

    public static boolean isPeliculaAlreadyStored(int id){

        Optional<Pelicula> pelicula=SampleDataGenerator
                .getPeliculasSample()
                .stream().filter(p->p.getIdPelicula()==id).findAny();
        System.out.println("stored: "+pelicula.get().getIdPelicula());
        if(pelicula.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
