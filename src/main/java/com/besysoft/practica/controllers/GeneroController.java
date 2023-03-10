package com.besysoft.practica.controllers;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.services.interfaces.GeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/generos")
public class GeneroController {


    private final GeneroService generoService;

    public GeneroController(GeneroService service){
        this.generoService=service;
    }

    @PostMapping()
    public ResponseEntity crearGenero(@RequestBody Genero genero){
        try {
           generoService.crearGenero(genero);
            return new ResponseEntity("Genero creado correctamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity obtenerGeneros(){
        try {
            return new ResponseEntity(generoService.obtenerTodos(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarGenero(@RequestBody Genero genero, @PathVariable int id){
        try {
            generoService.actualizarGenero(genero,id);
            return new ResponseEntity("genero actualizado correctamente",HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
