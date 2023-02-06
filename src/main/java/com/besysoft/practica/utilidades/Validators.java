package com.besysoft.practica.utilidades;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.repositories.memory.interfaces.GeneroRepository;
import com.besysoft.practica.repositories.memory.interfaces.PeliculaRepository;
import com.besysoft.practica.repositories.memory.interfaces.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class Validators {


    @Autowired
    GeneroRepository generoRepository;

    @Autowired
    PeliculaRepository peliculaRepository;

    @Autowired
    PersonajeRepository personajeRepository;


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

    public  boolean isPeliculaAlreadyStored(String peli){
        Optional<Pelicula> pelicula=peliculaRepository.getByTitle(peli);
        if(pelicula.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public  boolean isPersonajeAlreadyStored(String per)  {
        Optional<Personaje> personaje=personajeRepository.getByName(per);
        if(personaje.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public  boolean isPeliculaAlreadyStored(int id){

        Optional<Pelicula> pelicula=peliculaRepository.getById(id);

        if(pelicula.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    // En estos casos para validar también podría usar el trim() para quitar los espacios
    // me parece mejor que los géneros se escriban con el espacio y si no este incorrecto

    public  boolean isGeneroAlreadyStored(String genero){
        boolean isStored=false;
        Optional<Genero>gen=generoRepository.getByNombre(genero)
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
