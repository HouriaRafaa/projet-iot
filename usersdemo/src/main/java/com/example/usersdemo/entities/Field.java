package com.example.usersdemo.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Field {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @ManyToOne
    private Canal canal;

    @OneToMany(mappedBy = "field")
    private Collection<Valeur> valeur;


    public Field(String nom, Canal canal, Collection<Valeur> valeur) {
        this.nom = nom;
        this.canal = canal;
        this.valeur = valeur;
    }
}
