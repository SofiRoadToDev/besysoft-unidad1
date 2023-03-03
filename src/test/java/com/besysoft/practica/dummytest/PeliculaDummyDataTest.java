package com.besysoft.practica.dummytest;


import com.besysoft.practica.entities.Genero;
import com.besysoft.practica.entities.Pelicula;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class PeliculaDummyDataTest {

    private static Pelicula sirena;

    private static Pelicula gato;


    private static List<Pelicula> peliculasListTest=new ArrayList<>();

    private static boolean isInitialized=false;




    public static Pelicula getSirena() throws Exception {
        if(!isInitialized){
            initData();
        }
        return sirena;

    }

    public static Pelicula getGato() throws Exception {
        if(!isInitialized){
            initData();
        }
        return gato;
    }

    public static List<Pelicula>getListaPelisDummy() throws Exception {
        if(!isInitialized){
            initData();
        }
        return peliculasListTest;
    }

    public static void initData()throws Exception{
        sirena=new Pelicula();
        sirena.setTitulo("La Sirenita");
        sirena.setGenero(new Genero("musical"));
        sirena.setCalificacion(9);
        sirena.setFechaCreacion(new SimpleDateFormat("dd/MM/YYY").parse("23/12/2015"));


        gato=new Pelicula();
        gato.setTitulo("El gato con botas");
        gato.setGenero(new Genero("infantil"));
        gato.setCalificacion(7);
        gato.setFechaCreacion(new SimpleDateFormat("dd/MM/YYYY").parse("10/02/2023"));


        peliculasListTest.add(gato);
        peliculasListTest.add(sirena);

        isInitialized=true;
    }
}
