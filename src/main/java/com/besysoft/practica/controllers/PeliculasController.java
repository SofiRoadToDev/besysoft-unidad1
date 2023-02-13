package com.besysoft.practica.controllers;

import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.services.interfaces.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculasController {


    private final PeliculaService pelisService;


    public PeliculasController(PeliculaService pelisService){
        this.pelisService=pelisService;
    }
     @GetMapping()
    public ResponseEntity buscarTodas(){
         try {
             return new ResponseEntity(pelisService.obtenerTodos(),HttpStatus.OK);
         } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
         }
     }

    @GetMapping("/titulo")
    public ResponseEntity buscarPorTitulo(@RequestParam(name="titulo") String titulo){
        try {
            return new ResponseEntity(pelisService.buscarPorTitulo(titulo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/genero")
    public ResponseEntity buscarPorGenero(@RequestParam(name="genero") String genero){
        try {
            return new ResponseEntity(pelisService.buscarPorGenero(genero),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/fechas")
    public ResponseEntity getDesdeHastaFecha(@RequestParam String desde, @RequestParam String hasta) throws Exception {
        try {
           return new ResponseEntity<>( pelisService.buscarPorRangoFechas(desde,hasta),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/calificacion")
    public ResponseEntity getByRangoCalificacion( @RequestParam int desde, @RequestParam int hasta){
        try {
           return new ResponseEntity( pelisService.buscarPorRangoCalificacion(desde,hasta),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping()
    public ResponseEntity crearPelicula(@RequestBody Pelicula pelicula){

        try {
           return new ResponseEntity<>(pelisService.crearPelicula(pelicula),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    public  ResponseEntity actualizarPelicula(@RequestBody Pelicula pelicula, @PathVariable int id){

        try {
            return new ResponseEntity(pelisService.actualizarPelicula(pelicula),HttpStatus.ACCEPTED);
        } catch (Exception e) {
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarPelicula(@PathVariable Long id){
        return ResponseEntity.ok("pelicula borrada exitosamente");
    }
}
