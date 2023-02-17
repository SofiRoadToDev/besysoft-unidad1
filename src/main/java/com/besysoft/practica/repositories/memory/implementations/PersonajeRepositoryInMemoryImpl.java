package com.besysoft.practica.repositories.memory.implementations;

import com.besysoft.practica.dominio.PersonajeMem;
import com.besysoft.practica.repositories.memory.interfaces.PersonajeRepository;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PersonajeRepositoryInMemoryImpl implements PersonajeRepository {


    private List<PersonajeMem> personajeMems =new ArrayList<>();
    private static PersonajeMem capitanAmerica;
    private static PersonajeMem mulan;
    private static PersonajeMem shrek;
    private static PersonajeMem fiona;
    private static PersonajeMem burro;


    public PersonajeRepositoryInMemoryImpl(){
        capitanAmerica= new PersonajeMem("Capitan America",33,80,"Lo congelaron por años y después se unió a los vengadores");
        mulan= new PersonajeMem("Mulan",15,50," Fue a la guerra a pelear en lugar de su padre disfrazada de hombre");
        shrek= new PersonajeMem("Shrek",40,250,"Es un ogro gordo y feo pero de buen corazón que se casa con una princesa humana convertida en ogro");
        fiona=new PersonajeMem("Fiona",25,80," Es una princesa que se enamoró de Shrek y se volvió ogro para que él no se sienta mal");
        burro=new PersonajeMem("Burro",5,60," Es un amigo de shrek que se casó con una dragona y tuvo muchos hijos");
        personajeMems.add(capitanAmerica);
        personajeMems.add(mulan);
        personajeMems.add(shrek);
        personajeMems.add(fiona);
        personajeMems.add(burro);
    }
    @Override
    public Iterable<PersonajeMem> getAllFromSampleData() {
        return personajeMems;
    }

    @Override
    public  Optional<PersonajeMem> getById(int id)  {
        return personajeMems.stream().filter(p->p.getIdPersonaje()==id).findAny();
    }

    @Override
    public Iterable<PersonajeMem> getByAgeRange(int desde, int hasta) {
        return personajeMems
                .stream()
                .filter(p->p.getEdad()>=desde && p.getEdad()<=hasta )
                .collect(Collectors.toList());
    }

    @Override
    public  Optional<PersonajeMem> getByName(String name)  {
        return  personajeMems
                .stream()
                .filter(per -> per.getNombre().equals(name))
                .findAny();
    }

    @Override
    public PersonajeMem createPersonaje(PersonajeMem personajeMem) {
        personajeMem.setIdPersonaje(PersonajeMem.getIdCounter()+1);
        personajeMems.add(personajeMem);
        return personajeMem;
    }

    @Override
    public PersonajeMem updatePersonaje(PersonajeMem personajeMem, int id) {
        personajeMems.forEach(p->{
            if(p.getIdPersonaje()==id){
                int index=SampleDataGenerator.getPeliculasSample().indexOf(p);
                personajeMem.setIdPersonaje(id);
                personajeMems.set(index, personajeMem);

            }
        });
        return personajeMem;
    }
}
