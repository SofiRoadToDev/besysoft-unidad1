package com.besysoft.practica.utilidades.service;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.dominio.dto.PeliculaDTO;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {



    private List<PeliculaDTO> peliculas=new ArrayList<>();


    public List<PeliculaDTO> getAllPeliculas(){
        List<Pelicula>pelis =SampleDataGenerator.getPeliculasSample();

        pelis.stream().forEach(p->{
            PeliculaDTO pel=new PeliculaDTO(p.getIdPelicula(),p.getTitulo(),p.getFechaCreacion(),p.getCalificacion(),p.getGenero().getNombre());
            List<String>personajes=new ArrayList<>();
            p.getPersonajesAsociados().stream().forEach(per -> {personajes.add(per.getNombre());});
            pel.setPersonajes(personajes);
            peliculas.add(pel);
        });
        return peliculas;
    }


    public void crearPelicula(PeliculaDTO pelicula) throws Exception {
        /* Creo un objeto pelicula, acorde al modelo de negocio*/
        Pelicula peli= new Pelicula(pelicula.getTitulo(),pelicula.getFechaCreacion(),pelicula.getCalificacion());
        /*Busco si existe el genero que llego en el post*/
        Optional<Genero> genero=SampleDataGenerator.getGenerosSample().stream().filter(g ->g.getNombre().equals(pelicula.getGenero())).findAny();
        /*consulto y separo en dos listas los personajes que llegaron en el post y los que estaban ya guardados*/
        List<String>personajes=pelicula.getPersonajes();
        List<Personaje>storedPersonajes=SampleDataGenerator.getPersonajesSample();
        /*por cada personaje que llego en el post, si existe en lo guardado se agrega la referencia de su objeto a la lista del modelo de datos
        * si no, se crea un nuevo personaje y se agrega a la lista.
        * */
        personajes.stream().forEach(per->{
               for(String p : personajes){
                    if(storedPersonajes.contains(per)){
                        peli.getPersonajesAsociados().add(storedPersonajes.stream().filter(f->f.getNombre().equals(per)).findAny().get());
                    }else{
                        Personaje nuevo=new Personaje(per);
                        SampleDataGenerator.getPersonajesSample().add(nuevo);
                        peli.getPersonajesAsociados().add(nuevo);
                        nuevo.getPeliculasAsociadas().add(peli);
                    }
               }
        });
        /*Con el genero consulto si existe, true se vincula el objeto pelicula creado con el genero existente en modelo de datos*/
        if(genero.isPresent()){
            peli.setGenero(genero.get());
        }else{/*false lanzo una excepcion, mejor crear al genero desde un post apart. En el cliente seguramente habrá una lista para elegir el genero*/
            throw new Exception("El genero de pelicula ingresado no existe");
        }
        /*Una vez hechas las relaciones en el modelo de datos con el nuevo objeto pelicula, se agrega a la lista de peliculas guardada
        * y se agrega a la lista del DTO pelicula el objeto recibido PeliculaDTO
        * */
        SampleDataGenerator.getPeliculasSample().add(peli);
        pelicula.setId(peli.getIdPelicula());
        peliculas.add(pelicula);
        System.out.println(peli.toString());
        peliculas.forEach(p-> System.out.println(p.getTitulo()));
        SampleDataGenerator.getPeliculasSample().forEach(p-> System.out.println("sample: "+p.getTitulo()));
    }
}
