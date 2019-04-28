package com.example.usersdemo.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Triger {


    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String nom;


    private String apikey;

    @ManyToOne
    private AppUser user;

    @OneToMany(mappedBy = "triger")
    private Collection<Commande>commandes;


}
