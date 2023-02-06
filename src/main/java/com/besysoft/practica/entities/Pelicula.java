package com.besysoft.practica.entities;

import com.besysoft.practica.dominio.Genero;
import com.besysoft.practica.dominio.Personaje;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity @Data @NoArgsConstructor
@Table(name = "peliculas")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Date fechaCreacion;

    private Integer calificacion;

    private List<Personaje> personajesAsociados;

    private Genero genero;
}
