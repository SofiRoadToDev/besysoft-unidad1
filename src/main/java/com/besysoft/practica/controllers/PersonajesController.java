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
import java.util.stream.Collectors;

@RestController
public class PersonajesController {

    private SampleDataGenerator genData= SampleDataGenerator.getInstance();

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
    public ResponseEntity buscarPorEdad(@PathVariable int edad){
        /*Hay personajes ficticios con miles de años, como Dracula o las momias*/
        List<Personaje>personajes= new ArrayList<>();
        if((edad>0 && edad<10000)){
            personajes= genData.getPersonajesSample()
                    .stream()
                    .filter(p->p.getEdad()==edad)
                    .collect(Collectors.toList());
            if(personajes.isEmpty()){
                return new ResponseEntity<>("No se encontraron personajes con esa edad", HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity(personajes,HttpStatus.OK);
            }
        }else{
            return new ResponseEntity("Coloque una edad valida, un número entero, entre 0 y 10000", HttpStatus.BAD_REQUEST);
        }
    }

}
