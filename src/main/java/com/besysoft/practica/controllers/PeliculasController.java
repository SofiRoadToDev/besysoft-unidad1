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

import javax.swing.text.DateFormatter;
import javax.xml.validation.Validator;
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
    public List<Pelicula> buscarTodas(){
         List<Pelicula> peliculas=SampleDataGenerator.getPeliculasSample();
         return peliculas;
    }

    @GetMapping("/titulo")
    public ResponseEntity<Pelicula> buscarPorTitulo(@RequestParam(name="titulo") String titulo){
        boolean isTituloRight=titulo.matches("^([a-zA-Z]+\\s?[a-zA-Z]?[0-9]?)+$");
        if(isTituloRight){

            Optional<Pelicula>peli= SampleDataGenerator.getPeliculasSample()
                    .stream()
                    .filter(pelicula -> {
                        return pelicula.getTitulo().equals(titulo);
                    })
                    .findAny();
            if(peli.isPresent()){
                return new ResponseEntity<>(peli.get(),HttpStatus.OK);
            }else{
                return new ResponseEntity("No se ha encontrado esa pelicula", HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity("Solo se admiten letras en el titulo", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/genero")
    public ResponseEntity buscarPorGenero(@RequestParam(name="genero") String genero){
         boolean isOnlyLetters=genero.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");

         if(isOnlyLetters){
             Optional<Pelicula>peli=SampleDataGenerator.getPeliculasSample()
                     .stream()
                     .filter(pelicula -> {
                         return pelicula.getGenero().getNombre().equals(genero.toLowerCase());
                     }).findAny();

             if(peli.isPresent()){
                 return new ResponseEntity(peli.get(),HttpStatus.OK);
             }else{
                 return new ResponseEntity("No se encontraron peliculas para ese genero",HttpStatus.BAD_REQUEST);
             }
         }else{
             return new ResponseEntity<>("El genero debe estar compuesto solo de letras",HttpStatus.BAD_REQUEST);
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
}
