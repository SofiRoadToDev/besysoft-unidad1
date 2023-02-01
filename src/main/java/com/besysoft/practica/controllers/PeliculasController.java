package com.besysoft.practica.controllers;

import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.dominio.dto.PeliculaDTO;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import com.besysoft.practica.utilidades.Validators;
import com.besysoft.practica.utilidades.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/peliculas")
public class PeliculasController {

    @Autowired
    PeliculaService pelisService;



     @GetMapping()
    public List<PeliculaDTO> buscarTodas(){
         List<PeliculaDTO> peliculas=pelisService.getAllPeliculas();
         return peliculas;
    }

    @GetMapping("/titulo")
    public ResponseEntity buscarPorTitulo(@RequestParam(name="titulo") String titulo){
         Pelicula peli;
        try {
            peli=pelisService.buscarPeliculaPorTitulo(titulo).get();
            return new ResponseEntity(peli, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/genero")
    public ResponseEntity buscarPorGenero(@RequestParam(name="genero") String genero){
         List<Pelicula>peliculas;
        try {
            peliculas=pelisService.buscarPorGenero(genero).get();
            return new ResponseEntity(peliculas,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/fechas")
    public ResponseEntity getDesdeHastaFecha(@RequestParam String desde, @RequestParam String hasta){


        List<PeliculaDTO>peliculas=new ArrayList<>();

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
         List<Pelicula>pelis=new ArrayList<>();
        try {
            pelis=pelisService.buscarPorRangoCalificacion(desde,hasta);
            return new ResponseEntity(pelis,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping()
    public ResponseEntity crearPelicula(@RequestBody PeliculaDTO peliculaDTO){

        try {
            pelisService.crearPeliculaFull(peliculaDTO);
            return new ResponseEntity("pelicula creada correctamente",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/test")
    public List<Pelicula>getAll(){
         return SampleDataGenerator.getPeliculasSample();
    }

    @PutMapping("/{id}")
    public  ResponseEntity actualizarPelicula(@RequestBody PeliculaDTO peliculaDTO, @PathVariable int id){
        try {
            pelisService.actualizarPelicula(peliculaDTO,id);
            return new ResponseEntity("Pelicula actualizada correctamente",HttpStatus.ACCEPTED);
        } catch (Exception e) {
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
