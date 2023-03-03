package com.besysoft.practica.repositories.database;

import com.besysoft.practica.dummytest.PeliculaDummyDataTest;
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
    void setUp() throws Exception {


    }



    @Test
    void findByTitulo() throws Exception {
        //GIVEN
        peliculaRepositoryDB.save(PeliculaDummyDataTest.getSirena());
        //WHEN
        Optional<Pelicula> fantasma=peliculaRepositoryDB.findByTitulo("La Sirenita");
        //THEN
        assertThat(fantasma.isPresent()).isTrue();
        assertThat(fantasma.get().getCalificacion()).isEqualTo(9);
    }

    @Test
    void findByFechaCreacionBetween() throws Exception{
        //GIVEN
        peliculaRepositoryDB.save(PeliculaDummyDataTest.getSirena());
        //WHEN
        Date desde=new SimpleDateFormat("dd/MM/YYYY").parse("10/10/2010");
        Date hasta=new SimpleDateFormat("dd/MM/YYYY").parse("10/10/2018");
        List<Pelicula>peliculas= (List<Pelicula>) peliculaRepositoryDB.findByFechaCreacionBetween(desde,hasta);
       //THEN
        assertThat(peliculas.size()).isEqualTo(1);
    }

    @Test
    void findByGeneroNombre() throws Exception{
        //GIVEN
        peliculaRepositoryDB.save(PeliculaDummyDataTest.getSirena());
        peliculaRepositoryDB.save(PeliculaDummyDataTest.getGato());
        //WHEN
        List<Pelicula>peliculas= (List<Pelicula>) peliculaRepositoryDB.findByGeneroNombre("infantil");
        //THEN
        assertThat(peliculas.size()).isEqualTo(1);
    }

    @Test
    void findByCalificacionBetween() throws  Exception{
        //GIVEN
        peliculaRepositoryDB.save(PeliculaDummyDataTest.getSirena());
        peliculaRepositoryDB.save(PeliculaDummyDataTest.getGato());
        //WHEN
        List<Pelicula>peliculas= (List<Pelicula>) peliculaRepositoryDB.findByCalificacionBetween(6,8);
        //THEN
        assertThat(peliculas.size()).isEqualTo(1);
    }

    @AfterEach
    void tearDown() {
        peliculaRepositoryDB.deleteAll();
    }
}