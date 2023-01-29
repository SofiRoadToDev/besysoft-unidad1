package com.besysoft.practica.controllers;

import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personajes")
public class PersonajesController {



    @GetMapping()
    public List<Personaje> buscarTodos(){
        List<Personaje>personajes=new ArrayList<>();
        personajes=SampleDataGenerator.getPersonajesSample();

        return personajes;
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Personaje> buscarPorNombre(@PathVariable String nombre){
        boolean isOnlyLetters=nombre.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");

        if(isOnlyLetters){
          Optional<Personaje> p= SampleDataGenerator.getPersonajesSample()
                  .stream()
                  .filter(per -> per.getNombre().equals(nombre))
                  .findAny();
           if(p.isPresent()){
               return new ResponseEntity(p.get(), HttpStatus.OK);
           }else{
               return new ResponseEntity("personaje no encontrado",HttpStatus.BAD_REQUEST);
           }

        }else{
            return new ResponseEntity("El nombre solo puede contener letras",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/edad")
    public ResponseEntity buscarPorEdad(@RequestParam int desde,@RequestParam int hasta){
              List<Personaje>personajes=new ArrayList<>();
        if((desde>0 && hasta>0)){
            personajes= SampleDataGenerator.getPersonajesSample()
                    .stream()
                    .filter(p->p.getEdad()>=desde && p.getEdad()<=hasta )
                    .collect(Collectors.toList());
            if(personajes.isEmpty()){
                return new ResponseEntity<>("No se encontraron personajes con esa edad", HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity(personajes,HttpStatus.OK);
            }
        }else{
            return new ResponseEntity("Coloque rango de edad válido números enteros positivos", HttpStatus.BAD_REQUEST);
        }
    }

}
