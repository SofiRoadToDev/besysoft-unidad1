package com.besysoft.practica.services.implementations;

import com.besysoft.practica.entities.Genero;
import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.entities.Personaje;
import com.besysoft.practica.exceptions.PeliculaDoesntExistsException;
import com.besysoft.practica.exceptions.PeliculaExistsException;
import com.besysoft.practica.repositories.database.GeneroRepositoryDB;
import com.besysoft.practica.repositories.database.PeliculaRepositoryDB;
import com.besysoft.practica.repositories.database.PersonajeRepositoryDB;
import com.besysoft.practica.services.interfaces.PeliculaService;
import com.besysoft.practica.utilidades.Validators;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class PeliculaServiceImpl implements PeliculaService {


    private final PeliculaRepositoryDB peliculaRepository;
    private final GeneroRepositoryDB generoRepositoryDB;

    private final PersonajeRepositoryDB personajeRepositoryDB;


    private final Validators validators;
    @Override
    public Iterable<Pelicula> obtenerTodos() throws Exception {
        return peliculaRepository.findAll();
    }



    @Override
    public Optional<Pelicula> buscarPorTitulo(String titulo) throws Exception,PeliculaDoesntExistsException {
        boolean isTituloRight=titulo.matches("^([a-zA-Z]+\\s?[a-zA-Z]?[0-9]?)+$");
        Optional<Pelicula>peli;
        if(isTituloRight){
            peli= peliculaRepository.findByTitulo(titulo);
            if(peli.isPresent()){
                return peli;
            }else{
                log.info("No se ha encontrado la pelicula "+titulo);
                throw new PeliculaDoesntExistsException("No se ha encontrado esa pelicula");
            }
        }else{
            log.info("El titulo solo debe contener letras, espacios y numeros");
        }
            throw new Exception("El titulo solo debe contener letras, espacios y numeros ");
        }




    @Override
    public Iterable<Pelicula> buscarPorRangoCalificacion(int desde, int hasta) throws Exception {
        boolean isDesdeRight=desde>=1 && desde<=10;
        boolean isHastaRight=desde>=1 && desde<=10;
        boolean isDesdeMenor= desde<=hasta;

        if(isDesdeRight && isHastaRight && isDesdeMenor){
           return peliculaRepository.findByCalificacionBetween(desde,hasta);
        }else{
            log.info(" ingresó un rango inválido en calificación: "+desde+" "+hasta);
            throw new Exception(" La calificación debe ser un número entero entre 1y 10. Desde debe ser menor o igual que hasta");
        }


    }

    @Override
    public Iterable<Pelicula> buscarPorGenero(String genero) throws Exception {
        boolean isOnlyLetters=genero.matches("^([a-zA-Z]+\\s?[a-zA-Z]?)+$");
        if(isOnlyLetters) {
         return peliculaRepository.findByGeneroNombre(genero);
        }else {
            log.info("ingresó un género inválido: "+genero);
            throw new Exception("El genero debe estar compuesto solo por letras");
        }
    }


    @Override
    public Pelicula actualizarPelicula(Pelicula pelicula,Long id) throws Exception,PeliculaDoesntExistsException {
        if(validators.isPeliculaAlreadyStored(id)){
            Pelicula peliculaStored=peliculaRepository.findById(id).get();
            Optional<Genero>genero;
            if(pelicula.getGenero()!=null){
                genero= generoRepositoryDB.findByNombre(pelicula.getGenero().getNombre());
                if(genero.isPresent()){
                    peliculaStored.setGenero(genero.get());
                }else{
                    peliculaStored.setGenero(generoRepositoryDB.save(pelicula.getGenero()));
                }
            }
            if(pelicula.getPersonajesAsociados()!=null && !pelicula.getPersonajesAsociados().isEmpty()){
                pelicula.getPersonajesAsociados().forEach(per->{
                    Optional<Personaje>personaje=personajeRepositoryDB.findByNombre(per.getNombre());
                    if(personaje.isPresent()){
                        peliculaStored.getPersonajesAsociados().add(personaje.get());
                    }else{
                        peliculaStored.getPersonajesAsociados().add(personajeRepositoryDB.save(per));
                    }
                });
            }
            return peliculaRepository.save(peliculaStored);
        }else{
            log.info("No se encuentra ninguna pelicula con id: "+id);
            throw new PeliculaDoesntExistsException("El id proporcionado no corresponde a ninguna pelicula existente");
        }
    }

    @Override
    public Pelicula crearPelicula(Pelicula pelicula) throws Exception, PeliculaExistsException {
        if(validators.isPeliculaAlreadyStored(pelicula.getTitulo())){
            log.info("inento de crear película ya existente: "+pelicula.getTitulo());
            throw new PeliculaExistsException("Esa pelicula ya existe ");
        }
        if(!(pelicula.getCalificacion()>=1 && pelicula.getCalificacion()<=10)){
            log.info("Formato inválido de calificación al crear película, ingresó: "+pelicula.getCalificacion());
            throw new Exception("La calificacion es del 1 al 10, solo enteros");
        }
        Optional<Genero>genero;
        if(pelicula.getGenero()!=null){
            genero= generoRepositoryDB.findByNombre(pelicula.getGenero().getNombre());
            if(genero.isPresent()){
                pelicula.setGenero(genero.get());
            }else{
                pelicula.setGenero(generoRepositoryDB.save(pelicula.getGenero()));
            }
        }
        if(pelicula.getPersonajesAsociados()!=null && !pelicula.getPersonajesAsociados().isEmpty()){
            List<Personaje> personajesAsociados=new ArrayList<>();
            pelicula.getPersonajesAsociados().forEach(per->{
                Optional<Personaje>personaje=personajeRepositoryDB.findByNombre(per.getNombre());
                if(personaje.isPresent()){
                    personajesAsociados.add(personaje.get());
                }else{
                    personajesAsociados.add(personajeRepositoryDB.save(per));
                }
            });
            pelicula.setPersonajesAsociados(personajesAsociados);
        }
        return peliculaRepository.save(pelicula);
    }

    @Override
    public Iterable<Pelicula> buscarPorRangoFechas(String desde, String hasta) throws Exception {
        if(Validators.isDateRight(desde) && Validators.isDateRight(hasta)){
            DateFormat formatter=new SimpleDateFormat("ddMMYYYY");
            Date desdeF=formatter.parse(desde);
            Date hastaF=formatter.parse(hasta);
            return peliculaRepository.findByFechaCreacionBetween(desdeF,hastaF);
        }else{
            log.info("Se ingresó un formato de fecha inválido al buscar películas : "+desde+" "+hasta);
           throw  new Exception("Ingrese fecha válidas con el formato ddMMyyyy, por ejemplo 12102004 y que el año este entre 1900 y el actual");
        }
    }

    @Override
    public void borrarPelicula(Long id) {
        log.info("Se ha borrado la película id: "+id);
        peliculaRepository.deleteById(id);
    }
}
