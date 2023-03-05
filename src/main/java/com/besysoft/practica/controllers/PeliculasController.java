package com.besysoft.practica.controllers;

import com.besysoft.practica.dto.PeliculaDTO;
import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.exceptions.PeliculaDoesntExistsException;
import com.besysoft.practica.exceptions.PeliculaExistsException;
import com.besysoft.practica.mappers.PeliculaMapper;
import com.besysoft.practica.services.interfaces.PeliculaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
@AllArgsConstructor
@Api(value="Operaciones permitidas sobre Peliculas",tags = "Peliculas Controller")
public class PeliculasController {


    private final PeliculaService pelisService;



     @GetMapping()
     @ApiOperation(value = "Permite consultar la lista de películas")
    public ResponseEntity buscarTodas(){
         try {
             return new ResponseEntity(PeliculaMapper.mapToListPeliculaDTO((List<Pelicula>) pelisService.obtenerTodos()),HttpStatus.OK);
         } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
         }
     }

    @GetMapping("/titulo")
    @ApiOperation(value = "Permite buscar una película por título")
    public ResponseEntity buscarPorTitulo(@RequestParam(name="titulo") String titulo) throws Exception, PeliculaDoesntExistsException {
            return new ResponseEntity(PeliculaMapper.mapToPeliculaDTO(pelisService.buscarPorTitulo(titulo).get()), HttpStatus.OK);

    }

    @GetMapping("/genero")
    @ApiOperation(value = "Permite buscar una película por género")

    public ResponseEntity buscarPorGenero(@RequestParam(name="genero") String genero){
        try {
            return new ResponseEntity(PeliculaMapper.mapToListPeliculaDTO((List<Pelicula>) pelisService.buscarPorGenero(genero)),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/fechas")
    @ApiOperation(value = "Permite buscar una película por rango de fechas")

    public ResponseEntity getDesdeHastaFecha(@RequestParam String desde, @RequestParam String hasta) throws Exception {
        try {
           return new ResponseEntity<>( PeliculaMapper.mapToListPeliculaDTO((List<Pelicula>) pelisService.buscarPorRangoFechas(desde,hasta)),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/calificacion")
    @ApiOperation(value = "Permite buscar una película por rango de calificacion")

    public ResponseEntity getByRangoCalificacion( @RequestParam int desde, @RequestParam int hasta){
        try {
           return new ResponseEntity( PeliculaMapper.mapToListPeliculaDTO((List<Pelicula>) pelisService.buscarPorRangoCalificacion(desde,hasta)),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping()
    public ResponseEntity crearPelicula(@RequestBody PeliculaDTO peliculaDTO) throws Exception, PeliculaExistsException {
           return new ResponseEntity<>(PeliculaMapper.mapToPeliculaDTO(pelisService.crearPelicula(PeliculaMapper.mapToPelicula(peliculaDTO))),HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public  ResponseEntity actualizarPelicula(@RequestBody PeliculaDTO peliculaDTO, @PathVariable Long id) throws Exception,PeliculaExistsException {
            return new ResponseEntity(PeliculaMapper.mapToPeliculaDTO(pelisService.actualizarPelicula(PeliculaMapper.mapToPelicula(peliculaDTO),id)),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarPelicula(@PathVariable Long id){
        return ResponseEntity.ok("pelicula borrada exitosamente");
    }
}
