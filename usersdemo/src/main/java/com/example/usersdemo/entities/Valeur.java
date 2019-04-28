package com.example.usersdemo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity @Data @NoArgsConstructor
public class Valeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valeur;
    private Date date;



    @ManyToOne
    private Field field;


    public Valeur(double valeur, Field field , Date date) {
        this.valeur = valeur;
        this.field = field;
        this.date =date;
    }

}
