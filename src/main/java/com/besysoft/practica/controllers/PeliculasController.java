package com.besysoft.practica.controllers;

import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PeliculasController {

    private SampleDataGenerator genData= SampleDataGenerator.getInstance();

     @GetMapping("/peliculas")
    public List<Pelicula> buscarTodas(){
         List<Pelicula> peliculas=genData.getPeliculasSample();
         return peliculas;
    }

    @GetMapping("/peliculas/titulo/{titulo}")
    public ResponseEntity<Pelicula> buscarPorTitulo(@PathVariable(name="titulo") String titulo){
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

    @GetMapping("/peliculas/genero/{genero}")
    public ResponseEntity buscarPorGenero(@PathVariable(name="genero") String genero){
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
}
