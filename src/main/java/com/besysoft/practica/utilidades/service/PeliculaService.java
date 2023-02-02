package com.besysoft.practica.utilidades.service;

import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeliculaService {





    public List<Pelicula> getAllPeliculas(){
        return SampleDataGenerator.getPeliculasSample();
    }


    public void crearPelicula(Pelicula pelicula) throws Exception {
        if(Validators.isPeliculaAlreadyStored(pelicula.getTitulo())){
            throw new Exception("Esa pelicula ya existe ");
        }
        if(!(pelicula.getCalificacion()>=1 && pelicula.getCalificacion()<=10)){
            throw new Exception("La calificacion es del 1 al 10, solo enteros");
        }
        SampleDataGenerator.getPeliculasSample().add(pelicula);
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
    public void actualizarPelicula(Pelicula pelicula, int id) throws Exception{

        if(Validators.isPeliculaAlreadyStored(id)){
            SampleDataGenerator.getPeliculasSample()
                    .forEach(p->{
                        if(p.getIdPelicula()==id){
                            int index=SampleDataGenerator.getPeliculasSample().indexOf(p);
                            pelicula.setIdPelicula(id);
                            SampleDataGenerator.getPeliculasSample().set(index,pelicula);

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

    public List<Pelicula> buscarPorRangoCalificacion(int desde, int hasta) throws Exception{

        List<Pelicula>peliculas=new ArrayList<>();

        boolean isDesdeRight=desde>=1 && desde<=10;
        boolean isHastaRight=desde>=1 && desde<=10;
        boolean isDesdeMenor= desde<=hasta;

        if(isDesdeRight && isHastaRight && isDesdeMenor){
            peliculas=SampleDataGenerator.getPeliculasSample().stream()
                    .filter(p->p.getCalificacion()>=desde && p.getCalificacion()<=hasta)
                    .collect(Collectors.toList());
        }else{
            throw new Exception(" La calificación debe ser un número entero entre 1y 10. Desde debe ser menor o igual que hasta");
        }

        return peliculas;
    }


    //crea objeto pelicula con o sin id, metodo usado por  actualizar y tambien crear

}
