package com.besysoft.practica.repositories.memory.implementations;

import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.repositories.memory.interfaces.PersonajeRepository;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PersonajeRepositoryInMemoryImpl implements PersonajeRepository {


    private List<Personaje> personajes=new ArrayList<>();
    private static Personaje capitanAmerica;
    private static Personaje mulan;
    private static Personaje shrek;
    private static Personaje fiona;
    private static Personaje burro;


    public PersonajeRepositoryInMemoryImpl(){
        capitanAmerica= new Personaje("Capitan America",33,80,"Lo congelaron por años y después se unió a los vengadores");
        mulan= new Personaje("Mulan",15,50," Fue a la guerra a pelear en lugar de su padre disfrazada de hombre");
        shrek= new Personaje("Shrek",40,250,"Es un ogro gordo y feo pero de buen corazón que se casa con una princesa humana convertida en ogro");
        fiona=new Personaje("Fiona",25,80," Es una princesa que se enamoró de Shrek y se volvió ogro para que él no se sienta mal");
        burro=new Personaje("Burro",5,60," Es un amigo de shrek que se casó con una dragona y tuvo muchos hijos");
        personajes.add(capitanAmerica);
        personajes.add(mulan);
        personajes.add(shrek);
        personajes.add(fiona);
        personajes.add(burro);
    }
    @Override
    public Iterable<Personaje> getAllFromSampleData() {
        return personajes;
    }

    @Override
    public  Optional<Personaje> getById(int id)  {
        return personajes.stream().filter(p->p.getIdPersonaje()==id).findAny();
    }

    @Override
    public Iterable<Personaje> getByAgeRange(int desde, int hasta) {
        return personajes
                .stream()
                .filter(p->p.getEdad()>=desde && p.getEdad()<=hasta )
                .collect(Collectors.toList());
    }

    @Override
    public  Optional<Personaje> getByName(String name)  {
        return  personajes
                .stream()
                .filter(per -> per.getNombre().equals(name))
                .findAny();
    }

    @Override
    public Personaje createPersonaje(Personaje personaje) {
        personaje.setIdPersonaje(Personaje.getIdCounter()+1);
        personajes.add(personaje);
        return personaje;
    }

    @Override
    public Personaje updatePersonaje(Personaje personaje, int id) {
        personajes.forEach(p->{
            if(p.getIdPersonaje()==id){
                int index=SampleDataGenerator.getPeliculasSample().indexOf(p);
                personaje.setIdPersonaje(id);
                personajes.set(index,personaje);

            }
        });
        return personaje;
    }
}
