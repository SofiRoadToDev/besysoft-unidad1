package com.besysoft.practica.controllers;

import com.besysoft.practica.dto.GeneroDTO;
import com.besysoft.practica.entities.Genero;
import com.besysoft.practica.exceptions.GeneroDoesntExistsException;
import com.besysoft.practica.mappers.GeneroMapper;
import com.besysoft.practica.services.interfaces.GeneroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
@AllArgsConstructor
@Api(value = "Operaciones permitidas con Genero",tags="Genero Controller")
public class GeneroController {


    private final GeneroService generoService;


    @PostMapping()
    @ApiOperation(value = "Permite crear un género")
    public ResponseEntity crearGenero(@RequestBody GeneroDTO generoDTO) throws Exception {

            return new ResponseEntity(generoService.crearGenero(GeneroMapper.mapToGenero(generoDTO)), HttpStatus.CREATED);

    }

    @GetMapping()
    @ApiOperation(value = "Permite consultar todos los géneros")
    public ResponseEntity obtenerTodos() throws Exception {

            return new ResponseEntity(GeneroMapper.mapToListGeneroDTO((List<Genero>) generoService.obtenerTodos()),HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite actualizar un género")
    public ResponseEntity actualizarGenero(@RequestBody GeneroDTO generoDTO, @PathVariable Long id) throws Exception, GeneroDoesntExistsException {

            generoService.actualizarGenero(GeneroMapper.mapToGenero(generoDTO),id);
            return new ResponseEntity("genero actualizado correctamente",HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Permite borrar un género")
    public ResponseEntity borrarGenero(@PathVariable Long id){
        generoService.borrarGenero(id);
        return ResponseEntity.ok("Genero borrado exitosamente");
    }


}
