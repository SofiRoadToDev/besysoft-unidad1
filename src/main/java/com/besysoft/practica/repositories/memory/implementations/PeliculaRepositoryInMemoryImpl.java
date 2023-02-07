package com.besysoft.practica.repositories.memory.implementations;


import com.besysoft.practica.dominio.PeliculaMem;
import com.besysoft.practica.repositories.memory.interfaces.PeliculaRepository;
import com.besysoft.practica.utilidades.SampleDataGenerator;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PeliculaRepositoryInMemoryImpl implements PeliculaRepository {

    private List<PeliculaMem> peliculaMems =new ArrayList<>();
    private static PeliculaMem elPrimerVengador;
    private static PeliculaMem soldadoDeInvierno;
    private static PeliculaMem civilWar;
    private static PeliculaMem shrekFilm;
    private static PeliculaMem shrekAfeter;
    private static PeliculaMem mulanFilm;
    private static PeliculaMem mulan1;
    public PeliculaRepositoryInMemoryImpl(){

        elPrimerVengador=new PeliculaMem("El primer vengador", LocalDate.of(2011,11,2),8);
        soldadoDeInvierno=new PeliculaMem("Soldado de invierno", LocalDate.of(2013,10,12),5);
        civilWar=new PeliculaMem("Civil War",LocalDate.of(2015,8,23),9);
        shrekFilm=new PeliculaMem("Shrek1", LocalDate.of(2001,3,12),7);
        shrekAfeter=new PeliculaMem("Shrek Forever After", LocalDate.of(2010,12,1),9);
        mulanFilm= new PeliculaMem("Mulan live action",LocalDate.of(2020,07,4),10);
        mulan1= new PeliculaMem("Mulan Disney", LocalDate.of(1998,10,2),10);

        peliculaMems.add(elPrimerVengador);
        peliculaMems.add(soldadoDeInvierno);
        peliculaMems.add(civilWar);
        peliculaMems.add(shrekFilm);
        peliculaMems.add(shrekAfeter);
        peliculaMems.add(mulan1);
        peliculaMems.add(mulanFilm);
    }
    @Override
    public Iterable<PeliculaMem> getAll() {
        return peliculaMems;
    }

    @Override
    public Iterable<PeliculaMem> getByGenre(String genero) {
        return peliculaMems.stream()
                .filter(pelicula -> {
                    return pelicula.getGeneroMem().getNombre().equals(genero.toLowerCase());
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<PeliculaMem> getById(int id) {
       return peliculaMems.stream().filter(p->p.getIdPelicula()==id).findAny();
    }

    @Override
    public Iterable<PeliculaMem> getByRatingScale(int desde, int hasta) {
        return peliculaMems.stream()
                .filter(p->p.getCalificacion()>=desde && p.getCalificacion()<=hasta)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PeliculaMem> getByTitle(String title) {
        return peliculaMems
                .stream()
                .filter(pelicula -> {
                    return pelicula.getTitulo().equals(title);
                })
                .findAny();
    }

    @Override
    public PeliculaMem createPelicula(PeliculaMem peliculaMem) {
        peliculaMem.setIdPelicula(PeliculaMem.getIdCounter()+1);
        peliculaMems.add(peliculaMem);
        return peliculaMem;
    }

    @Override
    public Iterable<PeliculaMem> getByDates(String desde, String hasta) {
        var fechaDesde=LocalDate.parse(desde, DateTimeFormatter.ofPattern("ddMMyyyy"));
        var fechaHasta=LocalDate.parse(hasta,DateTimeFormatter.ofPattern("ddMMyyyy"));
        return peliculaMems.stream()
                .filter(p->
                        (p.getFechaCreacion().isEqual(fechaDesde)|| p.getFechaCreacion().isAfter(fechaDesde))
                                && (p.getFechaCreacion().isBefore(fechaHasta)))
                .collect(Collectors.toList());
    }

    @Override
    public PeliculaMem updatePelicula(PeliculaMem peliculaMem, int id) {
       peliculaMems.forEach(p->{
                    if(p.getIdPelicula()==id){
                        int index=SampleDataGenerator.getPeliculasSample().indexOf(p);
                        peliculaMem.setIdPelicula(id);
                        SampleDataGenerator.getPeliculasSample().set(index, peliculaMem);

                    }
                });
       return peliculaMem;
    }
}
