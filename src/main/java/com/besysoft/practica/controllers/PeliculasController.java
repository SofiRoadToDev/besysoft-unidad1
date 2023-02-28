package com.besysoft.practica.controllers;

import com.besysoft.practica.dto.PeliculaDTO;
import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.mappers.PeliculaMapper;
import com.besysoft.practica.services.interfaces.PeliculaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
@AllArgsConstructor
public class PeliculasController {


    private final PeliculaService pelisService;



     @GetMapping()
    public ResponseEntity buscarTodas(){
         try {
             return new ResponseEntity(PeliculaMapper.mapToListPeliculaDTO((List<Pelicula>) pelisService.obtenerTodos()),HttpStatus.OK);
         } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
         }
     }

    @GetMapping("/titulo")
    public ResponseEntity buscarPorTitulo(@RequestParam(name="titulo") String titulo){
        try {
            return new ResponseEntity(PeliculaMapper.mapToPeliculaDTO(pelisService.buscarPorTitulo(titulo).get()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/genero")
    public ResponseEntity buscarPorGenero(@RequestParam(name="genero") String genero){
        try {
            return new ResponseEntity(PeliculaMapper.mapToListPeliculaDTO((List<Pelicula>) pelisService.buscarPorGenero(genero)),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/fechas")
    public ResponseEntity getDesdeHastaFecha(@RequestParam String desde, @RequestParam String hasta) throws Exception {
        try {
           return new ResponseEntity<>( PeliculaMapper.mapToListPeliculaDTO((List<Pelicula>) pelisService.buscarPorRangoFechas(desde,hasta)),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/calificacion")
    public ResponseEntity getByRangoCalificacion( @RequestParam int desde, @RequestParam int hasta){
        try {
           return new ResponseEntity( PeliculaMapper.mapToListPeliculaDTO((List<Pelicula>) pelisService.buscarPorRangoCalificacion(desde,hasta)),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping()
    public ResponseEntity crearPelicula(@RequestBody PeliculaDTO peliculaDTO){

        try {
           return new ResponseEntity<>(pelisService.crearPelicula(PeliculaMapper.mapToPelicula(peliculaDTO)),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    public  ResponseEntity actualizarPelicula(@RequestBody PeliculaDTO peliculaDTO, @PathVariable Long id){

        try {
            return new ResponseEntity(pelisService.actualizarPelicula(PeliculaMapper.mapToPelicula(peliculaDTO),id),HttpStatus.ACCEPTED);
        } catch (Exception e) {
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarPelicula(@PathVariable Long id){
        return ResponseEntity.ok("pelicula borrada exitosamente");
    }
}
