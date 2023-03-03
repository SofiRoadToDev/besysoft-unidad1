package com.besysoft.practica.services.implementations;

import com.besysoft.practica.entities.Genero;
import com.besysoft.practica.entities.Pelicula;
import com.besysoft.practica.repositories.database.GeneroRepositoryDB;
import com.besysoft.practica.repositories.database.PeliculaRepositoryDB;
import com.besysoft.practica.repositories.database.PersonajeRepositoryDB;
import com.besysoft.practica.services.interfaces.GeneroService;
import com.besysoft.practica.services.interfaces.PeliculaService;
import com.besysoft.practica.utilidades.Validators;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
class GeneroServiceImplTest {


    @MockBean
    private PeliculaRepositoryDB repo;


    private Validators validator= new Validators();

    @MockBean
    private GeneroRepositoryDB genero;

    @Autowired
    private PeliculaService service;

    @Autowired
    private GeneroService generoService;

    @MockBean
    private PersonajeRepositoryDB personajeRepositoryDB;

    @BeforeEach
    void setUp() throws ParseException {
        Pelicula pelicula=new Pelicula();
        pelicula.setTitulo("El fantasma de la opera");
        pelicula.setGenero(new Genero("musical"));
        pelicula.setCalificacion(9);
        pelicula.setFechaCreacion(new SimpleDateFormat("dd/MM/YYY").parse("23/12/2015"));


        Pelicula pelicula2=new Pelicula();
        pelicula.setTitulo("El gato con botas");
        pelicula.setGenero(new Genero("infantil"));
        pelicula.setCalificacion(8);
        pelicula.setFechaCreacion(new SimpleDateFormat("dd/MM/YYYY").parse("10/02/2023"));

        repo=mock(PeliculaRepositoryDB.class);
        genero=mock(GeneroRepositoryDB.class);
        personajeRepositoryDB=mock(PersonajeRepositoryDB.class);

        service=new PeliculaServiceImpl(repo,genero,personajeRepositoryDB,validator);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void obtenerTodos() throws Exception {
      List<Pelicula> peliculas= (List<Pelicula>) service.obtenerTodos();
      verify(repo).findAll();
      assertThat( peliculas.size()).isEqualTo(2);
    }

    @Test
    void buscarPorId() throws ParseException{
        Pelicula pelicula=new Pelicula();
        pelicula.setTitulo("El fantasma de la opera");
        pelicula.setGenero(new Genero("musical"));
        pelicula.setCalificacion(9);
        pelicula.setFechaCreacion(new SimpleDateFormat("dd/MM/YYY").parse("23/12/2015"));

       given(repo.findById(1L)).willReturn(Optional.of(pelicula));

    }

    @Test
    void buscarPorNombre() {


    }

    @Test
    void crearGenero() throws Exception {
        Genero genero=new Genero("SciFi");

        generoService.crearGenero(genero);
        ArgumentCaptor<Genero>generoArgumentCaptor=ArgumentCaptor.forClass(Genero.class);



    }

    @Test
    void actualizarGenero() {
    }

    @Test
    void borrarGenero() {
    }
}