package com.besysoft.practica.services.implementations;

import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.repositories.database.PeliculaRepositoryDB;
import com.besysoft.practica.services.interfaces.PeliculaService;
import com.besysoft.practica.utilidades.Validators;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {


    private final PeliculaRepositoryDB peliculaRepository;

    private final Validators validators;
    public PeliculaServiceImpl(PeliculaRepositoryDB peliculaRepository,Validators validators){
        this.peliculaRepository=peliculaRepository;
        this.validators=validators;
    }
    @Override
    public Iterable<Pelicula> obtenerTodos() throws Exception {
        return peliculaRepository.findAll();
    }



    @Override
    public Optional<Pelicula> buscarPorTitulo(String titulo) throws Exception {
        boolean isTituloRight=titulo.matches("^([a-zA-Z]+\\s?[a-zA-Z]?[0-9]?)+$");
        Optional<Pelicula>peli;
        if(isTituloRight){
            peli= peliculaRepository.findByTitulo(titulo);
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
       return peliculaRepository.save(pelicula);
    }

    @Override
    public Iterable<Pelicula> buscarPorRangoCalificacion(int desde, int hasta) throws Exception {
        boolean isDesdeRight=desde>=1 && desde<=10;
        boolean isHastaRight=desde>=1 && desde<=10;
        boolean isDesdeMenor= desde<=hasta;

        if(isDesdeRight && isHastaRight && isDesdeMenor){
           return peliculaRepository.findByCalificacionBetween(desde,hasta);
        }else{
            throw new Exception(" La calificación debe ser un número entero entre 1y 10. Desde debe ser menor o igual que hasta");
        }


    }

    @Override
    public Iterable<Pelicula> buscarPorGenero(String genero) throws Exception {
        boolean isOnlyLetters=genero.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        if(isOnlyLetters) {
         return peliculaRepository.findByGeneroNombre(genero);
        }else throw  new Exception("El genero debe estar compuesto solo por letras");
    }

    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula) throws Exception {
        if(validators.isPeliculaAlreadyStored(pelicula.getId())){
            return peliculaRepository.save(pelicula);
        }else{
            throw new Exception("El id proporcionado no corresponde a ninguna pelicula existente");
        }
    }

    @Override
    public Iterable<Pelicula> buscarPorRangoFechas(String desde, String hasta) throws Exception {
        if(Validators.isDateRight(desde) && Validators.isDateRight(hasta)){
            return peliculaRepository.findByFechaCreacionBetween(desde,hasta);
        }else{
           throw  new Exception("Ingrese fecha válidas con el formato ddMMyyyy, por ejemplo 12102004 y que el año este entre 1900 y el actual");
        }
    }
}
