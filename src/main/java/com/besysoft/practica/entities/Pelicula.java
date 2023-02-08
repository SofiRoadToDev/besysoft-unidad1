package com.besysoft.practica.entities;



import lombok.Data;
import lombok.NoArgsConstructor;
import com.besysoft.practica.entities.Genero;
import  com.besysoft.practica.entities.Personaje;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity @Data @NoArgsConstructor
@Table(name = "peliculas")
public class Pelicula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Date fechaCreacion;

    private Integer calificacion;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "personajes_peliculas"
            ,joinColumns = @JoinColumn(name = "pelicula_id")
            ,inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private List<Personaje> personajesAsociados;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Genero genero;
}
