package com.besysoft.practica.utilidades.service;

import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PersonajeService {


    public List<Personaje> getAll(){
        return SampleDataGenerator.getPersonajesSample();
    }

    public Optional<Personaje> getByName(String nombre)throws Exception{
        boolean isOnlyLetters=nombre.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");

        if(isOnlyLetters){
            Optional<Personaje> p= SampleDataGenerator.getPersonajesSample()
                    .stream()
                    .filter(per -> per.getNombre().equals(nombre))
                    .findAny();
            if(p.isPresent()){
                return p;
            }else{
               throw new Exception("personaje no encontrado");
            }

        }else{
            throw new Exception("El nombre solo puede contener letras");
        }
    }


    public List<Personaje>getByAge(int desde, int hasta)throws Exception{
        List<Personaje>personajes=new ArrayList<>();
        if((desde>0 && hasta>0)&& desde<=hasta && hasta <20000){
            personajes= SampleDataGenerator.getPersonajesSample()
                    .stream()
                    .filter(p->p.getEdad()>=desde && p.getEdad()<=hasta )
                    .collect(Collectors.toList());
            if(personajes.isEmpty()){
                throw new Exception("No se encontraron personajes con esa edad");
            }
        }else{
            throw new Exception("Coloque rango de edad válido números enteros positivos y desde debe ser menor o igual que hasta. La edad máxima de los personajes es 20000 años");
        }
        return personajes;
    }

    public void crearPersonaje(Personaje personaje)throws Exception{
        if(!validator.isPersonajeAlreadyStored(personaje.getNombre())){
            if(!(personaje.getEdad()>0 && personaje.getEdad()<20000)){
                throw new Exception("La edad del personaje debe ser entre 0 y 20000");
            }
            SampleDataGenerator.getPersonajesSample().add(personaje);
        }else{
            throw new Exception("El personaje ya existe");
        }
    }

    public void actualizarPersonaje(Personaje personaje,int id) throws Exception{
        if(Validators.isPersonajeAlreadyStored(id)){
            SampleDataGenerator.getPersonajesSample()
                    .forEach(p -> {
                        if(p.getIdPersonaje()==id){
                            int index=SampleDataGenerator.getPersonajesSample().indexOf(p);
                            personaje.setIdPersonaje(id);
                            SampleDataGenerator.getPersonajesSample().set(index,personaje);
                        }
                    });
        }else{
            throw new Exception("No existe un personaje con ese id");
        }
    }


}
