package com.besysoft.practica.dummytest;

import com.besysoft.practica.entities.Genero;

import java.util.ArrayList;
import java.util.List;

public class GenroDummyDataTest {


    private static Genero aventura;

    private static Genero infantil;

    private static boolean isInitialized=false;
    private static List<Genero> generos=new ArrayList<>();




    public static Genero getAventura(){

        if(!isInitialized){
            initData();
        }
        return aventura;
    }


    public static Genero getInfantil(){
        if(!isInitialized){
            initData();
        }
        return infantil;
    }

    public static List<Genero>getGeneros(){
        if(!isInitialized){
            initData();
        }
        return generos;
    }


    private static void initData(){
        aventura=new Genero();

        aventura.setNombre("aventura");

        infantil=new Genero();
        infantil.setNombre("Infantil");

        generos.add(aventura);
        generos.add(infantil);

        isInitialized=true;
    }

}
