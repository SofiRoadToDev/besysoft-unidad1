package com.besysoft.practica.utilidades;

import com.besysoft.practica.dominio.GeneroMem;
import com.besysoft.practica.dominio.PeliculaMem;
import com.besysoft.practica.dominio.PersonajeMem;
import com.besysoft.practica.entities.Genero;
import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.entities.Personaje;
import com.besysoft.practica.repositories.database.GeneroRepositoryDB;
import com.besysoft.practica.repositories.database.PeliculaRepositoryDB;
import com.besysoft.practica.repositories.database.PersonajeRepositoryDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class Validators {


    @Autowired
    GeneroRepositoryDB generoRepository;

    @Autowired
    PeliculaRepositoryDB peliculaRepository;

    @Autowired
    PersonajeRepositoryDB personajeRepository;


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

    public  boolean isPeliculaAlreadyStored(String peli)throws Exception{
        Optional<Pelicula> pelicula=peliculaRepository.findByTitulo(peli);
        if(pelicula.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public  boolean isPersonajeAlreadyStored(String per) throws Exception {
        Optional<Personaje> personaje=personajeRepository.findByNombre(per);
        if(personaje.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public  boolean isPeliculaAlreadyStored(Long id){

        Optional<Pelicula> pelicula=peliculaRepository.findById(id);

        if(pelicula.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    // En estos casos para validar también podría usar el trim() para quitar los espacios
    // me parece mejor que los géneros se escriban con el espacio y si no este incorrecto

    public  boolean isGeneroAlreadyStored(String genero) throws Exception{
        boolean isStored=false;
        Optional<Genero>gen=generoRepository.findByNombre(genero);
        if(gen.isPresent()){
            isStored= true;
        }
        return isStored;
    }

    public  boolean isPersonajeAlreadyStored(Long id){
        Optional<Personaje> personaje=personajeRepository.findById(id);
        if(personaje.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
