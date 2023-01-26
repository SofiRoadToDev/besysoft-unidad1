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

    private SampleDataGenerator genData= SampleDataGenerator.getInstance();

     @GetMapping()
    public List<Pelicula> buscarTodas(){
         List<Pelicula> peliculas=genData.getPeliculasSample();
         return peliculas;
    }

    @GetMapping("/titulo")
    public ResponseEntity<Pelicula> buscarPorTitulo(@RequestParam(name="titulo") String titulo){
        boolean isTituloRight=titulo.matches("^([a-zA-Z]+\\s?[a-zA-Z]?[0-9]?)+$");
        if(isTituloRight){

            Optional<Pelicula>peli= genData.getPeliculasSample()
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
             Optional<Pelicula>peli=genData.getPeliculasSample()
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

             System.out.println("COntroller "+desde+" "+hasta);

             int dayDesde=Integer.parseInt(desde.substring(0,2)) ;
             int monthDesde=Integer.parseInt(desde.substring(2,4));
             int yearDesde=Integer.parseInt(desde.substring(4,8));



             int dayHasta=Integer.parseInt(hasta.substring(0,2)) ;
             int monthHasta=Integer.parseInt(hasta.substring(2,4));
             int yearHasta=Integer.parseInt(hasta.substring(4,8));

             var fechaDesde=LocalDate.of(dayDesde,monthDesde,yearDesde);
             var fechaHasta=LocalDate.of(dayHasta,monthHasta,yearHasta);

             System.out.println("desde "+fechaDesde+" Hasta "+fechaHasta);

             peliculas=pelisService.getAllPeliculas().stream()
                     .filter(p->
                             (p.getFechaCreacion().isEqual(fechaDesde)|| p.getFechaCreacion().isAfter(fechaDesde))
                             && (p.getFechaCreacion().isBefore(fechaHasta)))
                     .collect(Collectors.toList());
             return new ResponseEntity(peliculas,HttpStatus.OK);
         }else{
             return new ResponseEntity("Ingrese fecha válidas con el formato ddMMyyyy, por ejemplo 12102004",HttpStatus.BAD_REQUEST);
         }
    }
}
