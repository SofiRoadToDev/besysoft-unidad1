package com.besysoft.practica.controllers;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import com.besysoft.practica.utilidades.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    GeneroService generoService;

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
    public List<Genero> getAll(){
        return SampleDataGenerator.getGenerosSample();
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
