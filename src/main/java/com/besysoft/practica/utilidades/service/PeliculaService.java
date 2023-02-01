package com.besysoft.practica.utilidades.service;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.dominio.Personaje;
import com.besysoft.practica.dominio.dto.PeliculaDTO;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeliculaService {



    private List<PeliculaDTO> peliculas=new ArrayList<>();


    public List<PeliculaDTO> getAllPeliculas(){
        peliculas.clear();
        List<Pelicula>pelis =SampleDataGenerator.getPeliculasSample();
        pelis.forEach(p->{
            PeliculaDTO pel=new PeliculaDTO(p.getIdPelicula(),p.getTitulo(),p.getFechaCreacion(),p.getCalificacion(),p.getGenero().getNombre());
            List<String>personajes=new ArrayList<>();
            p.getPersonajesAsociados().forEach(per -> {
                if(!p.getPersonajesAsociados().contains(per.getNombre())){
                    personajes.add(per.getNombre());
                }
            });
            pel.setPersonajes(personajes);
            peliculas.add(pel);
        });
        return peliculas;
    }


    public void crearPeliculaFull(PeliculaDTO pelicula) throws Exception {
        if(Validators.isPeliculaAlreadyStored(pelicula.getTitulo())){
            throw new Exception("Esa pelicula ya existe ");
        }
        if(!(pelicula.getCalificacion()>=1 && pelicula.getCalificacion()<=10)){
            throw new Exception("La calificacion es del 1 al 10, solo enteros");
        }
        Pelicula peli=crearPelicula(pelicula,-1);
        //pelicula.setId(peli.getIdPelicula());
        //peliculas.add(pelicula);
        SampleDataGenerator.getPeliculasSample().add(peli);
    }


    public Optional<List<Pelicula>>buscarPorGenero(String genero) throws Exception{
        boolean isOnlyLetters=genero.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        if(isOnlyLetters) {
            Optional<List<Pelicula>> pelis = Optional.of(SampleDataGenerator.getPeliculasSample()
                    .stream()
                    .filter(pelicula -> {
                        return pelicula.getGenero().getNombre().equals(genero.toLowerCase());
                    }).collect(Collectors.toList()));
            if (pelis.get().size()>0) {
                return pelis;
            } else {
                throw new Exception("No existen peliculas con ese genero");
            }
        }else throw  new Exception("El genero debe estar compuesto solo por letras");
    }
    public void actualizarPelicula(PeliculaDTO peliculaDTO, int id) throws Exception{
        Pelicula peli=crearPelicula(peliculaDTO,id);
        if(Validators.isPeliculaAlreadyStored(id)){
            peliculas.forEach(pelicula -> {
                if(pelicula.getId()==id){
                    int index=peliculas.indexOf(pelicula);
                    peliculas.set(index,peliculaDTO);
                    peliculas.forEach(p-> System.out.println(p.getTitulo()));
                }
            });

            SampleDataGenerator.getPeliculasSample().forEach(p->{
                if(p.getIdPelicula()==id){
                    int index=SampleDataGenerator.getPeliculasSample().indexOf(p);
                    SampleDataGenerator.getPeliculasSample().set(index,peli);
                    SampleDataGenerator.getPeliculasSample().forEach(pe-> System.out.println(pe.getTitulo()));
                }
            });
        }else{
            throw new Exception("El id proporcionado no corresponde a ninguna pelicula existente");
        }

    }

    public Optional<Pelicula> buscarPeliculaPorTitulo(String titulo) throws Exception{

        boolean isTituloRight=titulo.matches("^([a-zA-Z]+\\s?[a-zA-Z]?[0-9]?)+$");
        Optional<Pelicula>peli;

        if(isTituloRight){
            peli= SampleDataGenerator.getPeliculasSample()
                    .stream()
                    .filter(pelicula -> {
                        return pelicula.getTitulo().equals(titulo);
                    })
                    .findAny();
            if(peli.isPresent()){
                return peli;
            }else{
                throw new Exception("No se ha encontrado esa pelicula");
            }
        }else{
            throw new Exception("El titulo solo debe contener letras, espacios y numeros ");
        }
    }


    //crea objeto pelicula con o sin id, metodo usado por  actualizar y tambien crear
    private Pelicula crearPelicula(PeliculaDTO pelicula, int id) throws Exception{
        Pelicula peli;
        /*-1 indica que debe crear el id*/
        if(id!=-1){
             peli= new Pelicula(id,pelicula.getTitulo(),pelicula.getFechaCreacion(),pelicula.getCalificacion());
        }else{
             peli= new Pelicula(pelicula.getTitulo(),pelicula.getFechaCreacion(),pelicula.getCalificacion());
        }

        Optional<Genero> genero=SampleDataGenerator.getGenerosSample().stream().filter(g ->g.getNombre().equals(pelicula.getGenero())).findAny();

        List<String>personajes=pelicula.getPersonajes();
        List<Personaje>storedPersonajes=SampleDataGenerator.getPersonajesSample();

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

        if(genero.isPresent()){
            peli.setGenero(genero.get());
        }else{
            throw new Exception("El genero de pelicula ingresado no existe");
        }
        pelicula.setId(peli.getIdPelicula());
        return peli;
    }
}
