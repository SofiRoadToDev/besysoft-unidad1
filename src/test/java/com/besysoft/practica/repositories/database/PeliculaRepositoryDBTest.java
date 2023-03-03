package com.besysoft.practica.repositories.database;

import com.besysoft.practica.entities.Genero;
import com.besysoft.practica.entities.Pelicula;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class PeliculaRepositoryDBTest {

    @Autowired
    PeliculaRepositoryDB peliculaRepositoryDB;

    @BeforeEach
    void setUp() throws ParseException {
        Pelicula pelicula=new Pelicula();
        pelicula.setTitulo("El fantasma de la opera");
        pelicula.setGenero(new Genero("musical"));
        pelicula.setCalificacion(9);
        pelicula.setFechaCreacion(new SimpleDateFormat("dd/MM/YYY").parse("23/12/2015"));
        peliculaRepositoryDB.save(pelicula);

        Pelicula pelicula2=new Pelicula();
        pelicula.setTitulo("El gato con botas");
        pelicula.setGenero(new Genero("infantil"));
        pelicula.setCalificacion(8);
        pelicula.setFechaCreacion(new SimpleDateFormat("dd/MM/YYYY").parse("10/02/2023"));
        peliculaRepositoryDB.save(pelicula);
    }



    @Test
    void findByTitulo() {
        Optional<Pelicula> fantasma=peliculaRepositoryDB.findByTitulo("El fantasma de la opera");
        assertThat(fantasma.isPresent()).isTrue();
        assertThat(fantasma.get().getCalificacion()).isEqualTo(9);
    }

    @Test
    void findByFechaCreacionBetween() throws ParseException{
        Date desde=new SimpleDateFormat("dd/MM/YYYY").parse("10/10/2010");
        Date hasta=new SimpleDateFormat("dd/MM/YYYY").parse("10/10/2018");
        List<Pelicula>peliculas= (List<Pelicula>) peliculaRepositoryDB.findByFechaCreacionBetween(desde,hasta);
        assertThat(peliculas.size()).isEqualTo(1);
    }

    @Test
    void findByGeneroNombre() {
        List<Pelicula>peliculas= (List<Pelicula>) peliculaRepositoryDB.findByGeneroNombre("infantil");
        assertThat(peliculas.size()).isEqualTo(1);
    }

    @Test
    void findByCalificacionBetween() {
        List<Pelicula>peliculas= (List<Pelicula>) peliculaRepositoryDB.findByCalificacionBetween(6,8);
        assertThat(peliculas.size()).isEqualTo(8);
    }

    @AfterEach
    void tearDown() {
        peliculaRepositoryDB.deleteAll();
    }
}