package com.example.usersdemo.web;


import com.example.usersdemo.dao.CommandeRepository;
import com.example.usersdemo.dao.TrigerRepository;
import com.example.usersdemo.entities.AppUser;
import com.example.usersdemo.entities.Commande;
import com.example.usersdemo.entities.Triger;
import com.example.usersdemo.service.AccountService;
import com.example.usersdemo.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TriggerController {

    @Autowired
    AccountService accountService;

    @Autowired
    TriggerService triggerService;

    @Autowired
    TrigerRepository trigerRepository;

    @Autowired
    CommandeRepository commandeRepository;

@RequestMapping(value = "/trigger")

    public void addTrigger(@RequestBody Map<String, Object> payload){

    Triger triger;
    AppUser appUser=accountService.loadUserById(Long.parseLong(String.valueOf(payload.get("userId"))));
      triger =triggerService.saveTrigger(
              payload.get("nom").toString(),
              appUser,
              null);

        trigerRepository.save(triger);

       payload.forEach((s, o) -> {
        if (s.contains("commande")){

            commandeRepository.save(new Commande(null,o.toString(),false, triger));
        }
    });


}




      }

