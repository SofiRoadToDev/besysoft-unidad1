package com.besysoft.practica.repositories.implementations;


import com.besysoft.practica.dominio.Pelicula;
import com.besysoft.practica.repositories.interfaces.PeliculaRepository;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PeliculaRepositoryInMemoryImpl implements PeliculaRepository {

    private List<Pelicula> peliculas=new ArrayList<>();
    private static Pelicula elPrimerVengador;
    private static Pelicula soldadoDeInvierno;
    private static Pelicula civilWar;
    private static Pelicula shrekFilm;
    private static Pelicula shrekAfeter;
    private static Pelicula mulanFilm;
    private static Pelicula mulan1;
    public PeliculaRepositoryInMemoryImpl(){

        elPrimerVengador=new Pelicula("El primer vengador", LocalDate.of(2011,11,2),8);
        soldadoDeInvierno=new Pelicula("Soldado de invierno", LocalDate.of(2013,10,12),5);
        civilWar=new Pelicula("Civil War",LocalDate.of(2015,8,23),9);
        shrekFilm=new Pelicula("Shrek1", LocalDate.of(2001,3,12),7);
        shrekAfeter=new Pelicula("Shrek Forever After", LocalDate.of(2010,12,1),9);
        mulanFilm= new Pelicula("Mulan live action",LocalDate.of(2020,07,4),10);
        mulan1= new Pelicula("Mulan Disney", LocalDate.of(1998,10,2),10);

        peliculas.add(elPrimerVengador);
        peliculas.add(soldadoDeInvierno);
        peliculas.add(civilWar);
        peliculas.add(shrekFilm);
        peliculas.add(shrekAfeter);
        peliculas.add(mulan1);
        peliculas.add(mulanFilm);
    }
    @Override
    public Iterable<Pelicula> getAll() {
        return peliculas;
    }

    @Override
    public Iterable<Pelicula> getByGenre(String genero) {
        return peliculas.stream()
                .filter(pelicula -> {
                    return pelicula.getGenero().getNombre().equals(genero.toLowerCase());
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<Pelicula> getById(int id) {
       return peliculas.stream().filter(p->p.getIdPelicula()==id).findAny();
    }

    @Override
    public Iterable<Pelicula> getByRatingScale(int desde, int hasta) {
        return peliculas.stream()
                .filter(p->p.getCalificacion()>=desde && p.getCalificacion()<=hasta)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pelicula> getByTitle(String title) {
        return peliculas
                .stream()
                .filter(pelicula -> {
                    return pelicula.getTitulo().equals(title);
                })
                .findAny();
    }

    @Override
    public Pelicula createPelicula(Pelicula pelicula) {
        pelicula.setIdPelicula(Pelicula.getIdCounter()+1);
        peliculas.add(pelicula);
        return pelicula;
    }

    @Override
    public Iterable<Pelicula> getByDates(String desde, String hasta) {

    }

    @Override
    public Pelicula updatePelicula(Pelicula pelicula, int id) {
       peliculas.forEach(p->{
                    if(p.getIdPelicula()==id){
                        int index=SampleDataGenerator.getPeliculasSample().indexOf(p);
                        pelicula.setIdPelicula(id);
                        SampleDataGenerator.getPeliculasSample().set(index,pelicula);

                    }
                });
       return pelicula;
    }
}
