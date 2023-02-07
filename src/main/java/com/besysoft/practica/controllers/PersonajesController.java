package com.besysoft.practica.controllers;

import com.besysoft.practica.dominio.PersonajeMem;
import com.besysoft.practica.services.interfaces.PersonajeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personajes")
public class PersonajesController {

  private final PersonajeService personajeService;

    public PersonajesController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping()
    public ResponseEntity buscarTodos() {
        try {
            return new ResponseEntity<>(personajeService.obtenerTodos(),HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nombre")
    public ResponseEntity buscarPorNombre(@RequestParam String nombre) {
        try {
            return new ResponseEntity(personajeService.buscarPorNombre(nombre),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/edad")
    public ResponseEntity buscarPorEdad(@RequestParam int desde, @RequestParam int hasta) {
        try {
            return new ResponseEntity(personajeService.buscarPorRangoEdad(desde,hasta),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity crearPersonaje(@RequestBody PersonajeMem personajeMem){
        try {
            personajeService.crearPersonaje(personajeMem);
            return new ResponseEntity("personaje creado correctamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarPersonaje(@RequestBody PersonajeMem personajeMem, @PathVariable int id){
        try {
           return new ResponseEntity(personajeService.actualizaPersonaje(personajeMem,id),HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



}