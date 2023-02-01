package com.besysoft.practica.controllers;

import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import com.besysoft.practica.utilidades.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personajes")
public class PersonajesController {

    @Autowired
    PersonajeService personajeService;

    @GetMapping()
    public List<Personaje> buscarTodos(){
        List<Personaje>personajes=new ArrayList<>();
        personajes=personajeService.getAll();

        return personajes;
    }

    @GetMapping("/nombre")
    public ResponseEntity buscarPorNombre(@RequestParam String nombre){
        try {
            personajeService.getByName(nombre);
            return new ResponseEntity(personajeService.getByName(nombre),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
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
