package com.besysoft.practica.controllers;

import com.besysoft.practica.dto.PersonajeDTO;
import com.besysoft.practica.entities.Personaje;
import com.besysoft.practica.exceptions.PersonajeDoesntExistsException;
import com.besysoft.practica.exceptions.PersonajeExistsException;
import com.besysoft.practica.mappers.PersonajeMapper;
import com.besysoft.practica.services.interfaces.PersonajeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personajes")
@Api(tags = "Personajes Controller",value = "Personajes Controller")
public class PersonajesController {

  private final PersonajeService personajeService;

    public PersonajesController(PersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping()
    @ApiOperation("Permite consultar todos los personajes")
    public ResponseEntity buscarTodos() {
        try {
            return new ResponseEntity<>(PersonajeMapper.mapToListPersonajeDTO((List<Personaje>) personajeService.obtenerTodos()),HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nombre")
    @ApiOperation("Permite un personaje por nombre")
    public ResponseEntity buscarPorNombre(@RequestParam String nombre) throws Exception,PersonajeDoesntExistsException {

            return new ResponseEntity(PersonajeMapper.mapToPersonajeDTO(personajeService.buscarPorNombre(nombre).get()),HttpStatus.OK);


    }

    @GetMapping("/edad")
    @ApiOperation("Permite consultar personajes por rango de edad")
    public ResponseEntity buscarPorEdad(@RequestParam int desde, @RequestParam int hasta) {
        try {
            return new ResponseEntity(PersonajeMapper.mapToListPersonajeDTO((List<Personaje>) personajeService.buscarPorRangoEdad(desde,hasta)),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity crearPersonaje(@RequestBody PersonajeDTO personajeDTO) throws Exception, PersonajeExistsException {

           return new ResponseEntity(PersonajeMapper.mapToPersonajeDTO(personajeService.crearPersonaje(PersonajeMapper.mapToPersonaje(personajeDTO))), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarPersonaje(@RequestBody PersonajeDTO personajeDTO, @PathVariable Long id) throws Exception, PersonajeDoesntExistsException {

           return new ResponseEntity(PersonajeMapper.mapToPersonajeDTO(personajeService.actualizaPersonaje(PersonajeMapper.mapToPersonaje(personajeDTO),id)),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity borrarPersonaje(@PathVariable Long id){
        return ResponseEntity.ok("personaje borrado exitosamente");
    }

}