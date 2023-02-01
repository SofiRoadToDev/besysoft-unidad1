package com.besysoft.practica.utilidades.service;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.stereotype.Service;

@Service
public class GeneroService {



    public void crearGenero(Genero genero) throws  Exception{
        boolean isOnlyLetters=genero.getNombre().matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        if(!isOnlyLetters){
            throw new Exception("El genero solo debe tener letras");
        }else{
            if(!Validators.isGeneroAlreadyStores(genero.getNombre())){

            }

        }

    }
}
