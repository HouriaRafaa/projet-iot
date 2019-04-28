package com.example.usersdemo.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class AppUser {


   @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public  AppUser(Long id){
     this.id=id;
    }

    @Column(unique = true)
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  String password;

    @Column(unique = true)
    private String email;
    private  boolean actived;


 @OneToMany(mappedBy ="appUser")
    private Collection<Canal> canals;

 @OneToMany(mappedBy = "user")
 private Collection<Triger>trigers;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();

 public AppUser(String userName, String password, boolean actived, Collection<Canal> canals, Collection<AppRole> roles) {
  this.userName = userName;
  this.password = password;
  this.actived = actived;
  this.canals = canals;
  this.roles = roles;
 }
}
