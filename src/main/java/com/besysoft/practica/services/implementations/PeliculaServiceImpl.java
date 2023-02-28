package com.besysoft.practica.services.implementations;

import com.besysoft.practica.entities.Genero;
import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.entities.Personaje;
import com.besysoft.practica.repositories.database.GeneroRepositoryDB;
import com.besysoft.practica.repositories.database.PeliculaRepositoryDB;
import com.besysoft.practica.repositories.database.PersonajeRepositoryDB;
import com.besysoft.practica.services.interfaces.PeliculaService;
import com.besysoft.practica.utilidades.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {


    private final PeliculaRepositoryDB peliculaRepository;
    private final GeneroRepositoryDB generoRepositoryDB;

    private final PersonajeRepositoryDB personajeRepositoryDB;

    Logger logger= LoggerFactory.getLogger(PeliculaServiceImpl.class);

    private final Validators validators;
    public PeliculaServiceImpl(PeliculaRepositoryDB peliculaRepository
            , GeneroRepositoryDB generoRepositoryDB,
                               PersonajeRepositoryDB personajeRepositoryDB,
                               Validators validators){
        this.peliculaRepository=peliculaRepository;
        this.generoRepositoryDB = generoRepositoryDB;
        this.personajeRepositoryDB = personajeRepositoryDB;
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
    public Pelicula actualizarPelicula(Pelicula pelicula, Long id) throws Exception {
        logger.info("id obtenido servicio: "+id);
        if(validators.isPeliculaAlreadyStored(id)){
            Optional<Genero>genero;
            if(pelicula.getGenero()!=null){
                genero= generoRepositoryDB.findById(pelicula.getGenero().getId());
                if(genero.isPresent()){
                    pelicula.setGenero(genero.get());
                }else{
                    pelicula.setGenero(generoRepositoryDB.save(pelicula.getGenero()));
                }
            }
            if(pelicula.getPersonajesAsociados()!=null && !pelicula.getPersonajesAsociados().isEmpty()){
                pelicula.getPersonajesAsociados().forEach(per->{
                    Optional<Personaje>personaje=personajeRepositoryDB.findById(per.getId());
                    if(personaje.isPresent()){
                        pelicula.getPersonajesAsociados().add(personaje.get());
                    }else{
                        pelicula.getPersonajesAsociados().add(personajeRepositoryDB.save(per));
                    }
                });
            }
            return peliculaRepository.save(pelicula);
        }else{
            throw new Exception("El id proporcionado no corresponde a ninguna pelicula existente");
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
           throw  new Exception("Ingrese fecha válidas con el formato ddMMyyyy, por ejemplo 12102004 y que el año este entre 1900 y el actual");
        }
    }

    @Override
    public void borrarPelicula(Long id) {
        peliculaRepository.deleteById(id);
    }
}
