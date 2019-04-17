package com.example.usersdemo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Canal {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private Date dateCreation;

    private String cleLecture;
    private String cleEcriture;

    @ManyToOne
    private AppUser appUser;

    @OneToMany(mappedBy ="canal")
    private Collection<Field> fields = new ArrayList<>();







}
