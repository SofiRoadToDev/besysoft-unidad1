package com.besysoft.practica.services.implementations;

import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.repositories.interfaces.PeliculaRepository;
import com.besysoft.practica.services.interfaces.PeliculaService;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeliculaServiceImpl implements PeliculaService {


    private final PeliculaRepository peliculaRepository;

    private final Validators validators;
    public PeliculaServiceImpl(PeliculaRepository peliculaRepository,Validators validators){
        this.peliculaRepository=peliculaRepository;
        this.validators=validators;
    }
    @Override
    public Iterable<Pelicula> obtenerTodos() throws Exception {
        return peliculaRepository.getAll();
    }

    @Override
    public Optional<Pelicula> buscarPorId(int id) throws Exception {
        return peliculaRepository.getById(id);
    }

    @Override
    public Optional<Pelicula> buscarPorTitulo(String titulo) throws Exception {
        boolean isTituloRight=titulo.matches("^([a-zA-Z]+\\s?[a-zA-Z]?[0-9]?)+$");
        Optional<Pelicula>peli;
        if(isTituloRight){
            peli= peliculaRepository.getByTitle(titulo);
            if(peli.isPresent()){
                return peli;
            }else{
                throw new Exception("No se ha encontrado esa pelicula");
            }
        }else{
            throw new Exception("El titulo solo debe contener letras, espacios y numeros ");
        }
    }

    @Override
    public Pelicula crearPelicula(Pelicula pelicula) throws Exception {
        if(validators.isPeliculaAlreadyStored(pelicula.getTitulo())){
            throw new Exception("Esa pelicula ya existe ");
        }
        if(!(pelicula.getCalificacion()>=1 && pelicula.getCalificacion()<=10)){
            throw new Exception("La calificacion es del 1 al 10, solo enteros");
        }
       return peliculaRepository.createPelicula(pelicula);
    }

    @Override
    public Iterable<Pelicula> buscarPorRangoCalificacion(int desde, int hasta) throws Exception {
        boolean isDesdeRight=desde>=1 && desde<=10;
        boolean isHastaRight=desde>=1 && desde<=10;
        boolean isDesdeMenor= desde<=hasta;

        if(isDesdeRight && isHastaRight && isDesdeMenor){
           return peliculaRepository.getByRatingScale(desde,hasta);
        }else{
            throw new Exception(" La calificación debe ser un número entero entre 1y 10. Desde debe ser menor o igual que hasta");
        }


    }

    @Override
    public Iterable<Pelicula> buscarPorGenero(String genero) throws Exception {
        boolean isOnlyLetters=genero.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        if(isOnlyLetters) {
         return peliculaRepository.getByGenre(genero);
        }else throw  new Exception("El genero debe estar compuesto solo por letras");
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula, int id) throws Exception {
        if(validators.isPeliculaAlreadyStored(id)){
            return peliculaRepository.updatePelicula(pelicula,id);
        }else{
            throw new Exception("El id proporcionado no corresponde a ninguna pelicula existente");
        }
    }
}
