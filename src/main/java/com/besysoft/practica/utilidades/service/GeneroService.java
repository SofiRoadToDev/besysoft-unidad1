package com.besysoft.practica.utilidades.service;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeneroService {



    public void crearGenero(Genero genero) throws  Exception{
        boolean isOnlyLetters=genero.getNombre().matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        if(!isOnlyLetters){
            throw new Exception("El genero solo debe tener letras");
        }else{
            if(Validators.isGeneroAlreadyStores(genero.getNombre())){
               throw new Exception("Ese género ya existe");
            }else{
                genero.setIdGenero(Genero.getIdCounter()+1);
                SampleDataGenerator.getGenerosSample().add(genero);
            }

        }

    }


    public void actualizarGenero(Genero genero,int id) throws Exception{
        boolean isOnlyLetters=genero.getNombre().matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        if(!isOnlyLetters){
            throw new Exception("El nombre del genero solo debe tener letras");
        }else{
            Optional<Genero> gen=SampleDataGenerator.getGenerosSample()
                    .stream()
                    .filter(g->g.getIdGenero()==id)
                    .findAny();
            if(gen.isPresent()){
                SampleDataGenerator.getGenerosSample().forEach(g->{
                    if(g.getIdGenero()==id){
                        int i=SampleDataGenerator.getGenerosSample().indexOf(g);
                        genero.setIdGenero(g.getIdGenero());
                        SampleDataGenerator.getGenerosSample().set(i,genero);
                    }
                });
            }else{
                throw new Exception("El id no corresponde a ningún género existente");
            }

        }
    }
}
