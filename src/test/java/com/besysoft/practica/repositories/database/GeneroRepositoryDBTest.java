package com.besysoft.practica.repositories.database;

import com.besysoft.practica.dummytest.GenroDummyDataTest;
import com.besysoft.practica.entities.Genero;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
@DataJpaTest
public class GeneroRepositoryDBTest {


    @Autowired
    GeneroRepositoryDB repo;
    @Test
    public void buscarPorNombre() throws Exception {

        //GIVEN
        repo.save(GenroDummyDataTest.getAventura());

        //WHEN
        Genero av=repo.findByNombre("aventura").get();

        //THEN
        assertThat(av).isExactlyInstanceOf(Genero.class);

        assertThat(av.getNombre()).isEqualTo("aventura");
    }
}
