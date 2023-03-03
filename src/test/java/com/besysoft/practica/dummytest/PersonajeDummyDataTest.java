package com.besysoft.practica.dummytest;

import com.besysoft.practica.entities.Personaje;

import java.util.ArrayList;
import java.util.List;
public class PersonajeDummyDataTest {

    private static Personaje mulan;

    private static Personaje ironMan;

    private static List<Personaje>personajes= new ArrayList<>();

    private static boolean isInitialized=false;

    private static void initData(){
        mulan=new Personaje();
        mulan.setHistoria("Se fue a la guerra");
        mulan.setEdad(15);
        mulan.setPeso(45);
        mulan.setNombre("Mulan");

        ironMan=new Personaje();
        ironMan.setNombre("IronMan");
        ironMan.setPeso(80);
        ironMan.setEdad(35);
        ironMan.setHistoria("Jarvis");

        personajes.add(mulan);
        personajes.add(ironMan);
        isInitialized=true;
    }

    public static Personaje getMulan() {
        if(!isInitialized){
            initData();
        }
        return mulan;
    }

    public static Personaje getIronMan() {
        if(!isInitialized){
            initData();
        }
        return ironMan;
    }

    public static List<Personaje> getPersonajes() {
        if(!isInitialized){
            initData();
        }
        return personajes;
    }
}
