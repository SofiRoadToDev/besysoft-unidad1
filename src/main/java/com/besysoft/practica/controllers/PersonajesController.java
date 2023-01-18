package com.besysoft.practica.controllers;

import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class PersonajesController {

    private SampleDataGenerator genData= new SampleDataGenerator();
    static Logger logger= Logger.getLogger(PersonajesController.class.getName());
    @GetMapping("/personajes")
    public List<Personaje> buscarTodos(){
        List<Personaje>personajes=new ArrayList<>();
        personajes=genData.getPersonajesSample();

        return personajes;
    }

    @GetMapping("/personajes/nombre/{nombre}")
    public ResponseEntity<Personaje> buscarPorNombre(@PathVariable String nombre){
        boolean isOnlyLetters=nombre.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");

        if(isOnlyLetters){
          Optional<List<Personaje>> p= Optional.of(genData.getPersonajesSample()
                  .stream()
                  .filter(per -> per.getNombre().equals(nombre))
                  .collect(Collectors.toList()));
           if(p.get().size()>0){
               return new ResponseEntity(p.get().get(0), HttpStatus.OK);
           }else{
               return new ResponseEntity("personaje no encontrado",HttpStatus.NOT_FOUND);
           }

        }else{
            return new ResponseEntity("El nombre solo puede contener letras",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/personajes/edad/{edad}")
    public List<Personaje> buscarPorEdad(@PathVariable int edad){
        List<Personaje>personajes= new ArrayList<>();
        personajes= genData.getPersonajesSample()
                .stream()
                .filter(p->p.getEdad()==edad)
                .collect(Collectors.toList());
        return personajes;
    }

}
