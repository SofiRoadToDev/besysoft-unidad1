package com.besysoft.practica.controllers;

import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class PeliculasController {

    private SampleDataGenerator genData= new SampleDataGenerator();
    static Logger logger= Logger.getLogger(PeliculasController.class.getName());



     @GetMapping("/peliculas")
    public List<Pelicula> buscarTodas(){
         logger.info("tamañp del arreglo: "+genData.getGenerosSample().size());
         List<Pelicula> peliculas=genData.getPeliculasSample();
         return peliculas;
    }

    @GetMapping("/peliculas/titulo/{titulo}")
    public ResponseEntity<Pelicula> buscarPorTitulo(@PathVariable(name="titulo") String titulo){


        boolean isTituloRight=titulo.matches("^([a-zA-Z]+\\s?[a-zA-Z]?[0-9]?)+$");// ^([a-zA-Z]$[0-9]?)+$
        //^[a-zA-Z]+$
        if(isTituloRight){

            Optional<List<Pelicula>>peli= Optional.of(genData.getPeliculasSample()
                    .stream()
                    .filter(pelicula -> {
                        return pelicula.getTitulo().equals(titulo);
                    })
                    .collect(Collectors.toList()));
            if(peli.get().size()>0){
                return new ResponseEntity<>(peli.get().get(0),HttpStatus.OK);
            }else{
                return new ResponseEntity("No se ha encontrado esa pelicula", HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity("Solo se admiten letras en el titulo", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/peliculas/genero/{genero}")
    public ResponseEntity buscarPorGenero(@PathVariable(name="genero") String genero){
         boolean isOnlyLetters=genero.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
         List<Pelicula>peliculas=new ArrayList<>();
         if(isOnlyLetters){
             peliculas=genData.getPeliculasSample()
                     .stream()
                     .filter(pelicula -> {
                         return pelicula.getGenero().getNombre().equals(genero.toLowerCase());
                     })
                     .collect(Collectors.toList());
             if(peliculas.size()>0){
                 return new ResponseEntity(peliculas,HttpStatus.OK);
             }else{
                 return new ResponseEntity("No se encontraron peliculas para ese genero",HttpStatus.NOT_FOUND);
             }
         }else{
             return new ResponseEntity<>("El genero debe estar compuesto solo de letras",HttpStatus.BAD_REQUEST);
         }
    }


}
