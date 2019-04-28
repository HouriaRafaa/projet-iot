package com.example.usersdemo.service;

import com.example.usersdemo.entities.AppUser;
import com.example.usersdemo.entities.Commande;
import com.example.usersdemo.entities.Triger;

import java.util.List;

public interface TriggerService {

    public Triger saveTrigger(String nom, AppUser user, List<Commande>
                               commandes);
}
