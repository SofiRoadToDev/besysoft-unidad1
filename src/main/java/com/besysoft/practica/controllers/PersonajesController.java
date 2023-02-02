package com.besysoft.practica.controllers;

import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.utilidades.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personajes")
public class PersonajesController {

    @Autowired
    PersonajeService personajeService;

    @GetMapping()
    public List<Personaje> buscarTodos() {
        List<Personaje> personajes = new ArrayList<>();
        personajes = personajeService.getAll();

        return personajes;
    }

    @GetMapping("/nombre")
    public ResponseEntity buscarPorNombre(@RequestParam String nombre) {
        try {
            return new ResponseEntity(personajeService.getByName(nombre), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/edad")
    public ResponseEntity buscarPorEdad(@RequestParam int desde, @RequestParam int hasta) {
        try {
            return new ResponseEntity(personajeService.getByAge(desde, hasta), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping()
    public ResponseEntity crearPersonaje(@RequestBody Personaje personaje){
        try {
            personajeService.crearPersonaje(personaje);
            return new ResponseEntity("personaje creado correctamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarPersonaje(@RequestBody Personaje personaje, @PathVariable int id){
        try {
            personajeService.actualizarPersonaje(personaje,id);
            return new ResponseEntity("personaje actualizado correctamente",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



}