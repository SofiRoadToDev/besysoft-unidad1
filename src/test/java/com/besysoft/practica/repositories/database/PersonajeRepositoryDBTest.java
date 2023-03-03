package com.besysoft.practica.repositories.database;

import com.besysoft.practica.dummytest.PersonajeDummyDataTest;
import com.besysoft.practica.entities.Personaje;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonajeRepositoryDBTest {

    @Autowired
    PersonajeRepositoryDB repo;

    @Test
    void findByNombre() {
        //GIVEN
        repo.save(PersonajeDummyDataTest.getMulan());
        repo.save(PersonajeDummyDataTest.getIronMan());
        //WHEN
        Optional<Personaje>mulan=repo.findByNombre("Mulan");
        //THEN
        assertThat(((Optional<?>) mulan).isPresent()).isTrue();
        assertThat(mulan.get().getPeso()).isEqualTo(45);
    }

    @Test
    void findByEdadBetween() throws Exception {
        //GIVEN
        repo.save(PersonajeDummyDataTest.getIronMan());
        repo.save(PersonajeDummyDataTest.getMulan());
        //WHEN
        List<Personaje>personajes= (List<Personaje>) repo.findByEdadBetween(25,40);

        //THEN
        assertThat(personajes.size()).isEqualTo(1);
        assertThat(personajes.get(0).getEdad()).isEqualTo(35);
    }
}