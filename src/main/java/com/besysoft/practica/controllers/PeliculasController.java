package com.besysoft.practica.controllers;

import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import com.besysoft.practica.utilidades.Validators;
import com.besysoft.practica.services.interfaces.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity getDesdeHastaFecha(@RequestParam String desde, @RequestParam String hasta){
         if(Validators.isDateRight(desde) && Validators.isDateRight(hasta)){

             var fechaDesde=LocalDate.parse(desde,DateTimeFormatter.ofPattern("ddMMyyyy"));
             var fechaHasta=LocalDate.parse(hasta,DateTimeFormatter.ofPattern("ddMMyyyy"));

             peliculas=pelisService.getAllPeliculas().stream()
                     .filter(p->
                             (p.getFechaCreacion().isEqual(fechaDesde)|| p.getFechaCreacion().isAfter(fechaDesde))
                             && (p.getFechaCreacion().isBefore(fechaHasta)))
                     .collect(Collectors.toList());
             return new ResponseEntity(peliculas,HttpStatus.OK);
         }else{
             return new ResponseEntity("Ingrese fecha válidas con el formato ddMMyyyy, por ejemplo 12102004 y que el año este entre 1900 y el actual",HttpStatus.BAD_REQUEST);
         }
    }

    @GetMapping("/calificacion")
    public ResponseEntity getByRangoCalificacion( @RequestParam int desde, @RequestParam int hasta){
        try {
            return new ResponseEntity(pelisService.buscarPorRangoCalificacion(desde,hasta),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
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

    @GetMapping("/test")
    public List<Pelicula>getAll(){
         return SampleDataGenerator.getPeliculasSample();
    }

    @PutMapping("/{id}")
    public  ResponseEntity actualizarPelicula(@RequestBody Pelicula pelicula, @PathVariable int id){

        try {
            return new ResponseEntity(pelisService.actualizarPelicula(pelicula,id),HttpStatus.ACCEPTED);
        } catch (Exception e) {
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
