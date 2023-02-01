package com.besysoft.practica.utilidades.service;

import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PersonajeService {


    public List<Personaje> getAll(){
        return SampleDataGenerator.getPersonajesSample();
    }

    public Optional<Personaje> getByName(String nombre)throws Exception{
        boolean isOnlyLetters=nombre.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");

        if(isOnlyLetters){
            Optional<Personaje> p= SampleDataGenerator.getPersonajesSample()
                    .stream()
                    .filter(per -> per.getNombre().equals(nombre))
                    .findAny();
            if(p.isPresent()){
                return p;
            }else{
               throw new Exception("personaje no encontrado");
            }

        }else{
            throw new Exception("El nombre solo puede contener letras");
        }
    }

}
