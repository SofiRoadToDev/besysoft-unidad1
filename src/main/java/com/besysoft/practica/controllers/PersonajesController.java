package com.besysoft.practica.controllers;

import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonajesController {

    private SampleDataGenerator genData= new SampleDataGenerator();
    @GetMapping("/personajes")
    public List<Personaje> buscarTodos(){
        List<Personaje>personajes=new ArrayList<>();
        personajes=genData.getPersonajesSample();

        return personajes;
    }

    @GetMapping("/personajes/nombre/{nombre}")
    public Personaje buscarPorNombre(@PathVariable String nombre){

        return genData.getPersonajesSample()
                .stream()
                .filter(p->p.getNombre().equals(nombre))
                .collect(Collectors.toList()).get(0);
    }

    @GetMapping("/personajes/edad/{edad}")
    public List<Personaje> buscarPorEdad(@PathVariable int edad){
        List<Personaje>personajes= new ArrayList<>();
        personajes= genData.getPersonajesSample()
                .stream()
                .filter(p->p.getEdad()==edad)
                .collect(Collectors.toList());
        return personajes;
    }

}
