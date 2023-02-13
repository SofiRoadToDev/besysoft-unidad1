package com.besysoft.practica.entities;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity @Data @NoArgsConstructor
@Table(name = "peliculas")
public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    private Integer calificacion;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "personajes_peliculas"
            ,joinColumns = @JoinColumn(name = "pelicula_id")
            ,inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private List<Personaje> personajesAsociados;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id")
    private Genero genero;
}
