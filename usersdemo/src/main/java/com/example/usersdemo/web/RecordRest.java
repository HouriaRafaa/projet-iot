package com.example.usersdemo.web;

import com.example.usersdemo.dao.*;
import com.example.usersdemo.entities.*;
import com.pusher.rest.Pusher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RecordRest {


    @Autowired
    CanalRepository canalRepository;
    @Autowired
    FieldRepository fieldRepository;
    @Autowired
    ValeurRepository valeurRepository;

    @Autowired
    TrigerRepository trigerRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @GetMapping("/record")
    @ResponseBody
    public String updateCanal(@RequestParam Map<String,String> allParams) {

       Canal canal = canalRepository.findCanalByCleEcriture(allParams.get("key"));

        Pusher pusher = new Pusher("762880", "84bee67aad46ed497369", "5017a5ee0387085255ae");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);

        System.out.println("pusher ::::::::" + pusher.toString());

        pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", "hello world"));

      //  pusher.Triger("my-channel", "my-event", Collections.singletonMap("message", "hello world"));

        for(Field field:canal.getFields()){
            for (Map.Entry<String, String> entry : allParams.entrySet()){
                if (field.getNom().equalsIgnoreCase(entry.getKey())){
                    Double data = Double.parseDouble(entry.getValue());
                    Valeur valeur= new Valeur(data,field , new Date());
                    valeurRepository.save(valeur);
                }
            }

        }
       return " " + allParams.entrySet();

    }

    @RequestMapping(value = "/read", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Valeur2> readData(@RequestParam Map<String,String> allParams ) {



        List<Valeur> list=null;
        List<Valeur2> valeur = new ArrayList<Valeur2>();

        Canal canal = canalRepository.findCanalByCleLecture(allParams.get("key"));

        if ( canal!=null && canal.getFields().size()!=0) {
            for(Field field:canal.getFields()){
                if (field.getNom().equalsIgnoreCase(allParams.get("field"))){
                     list = new ArrayList(field.getValeur());

                     for(Valeur v:list){
                         Valeur2 valeur2 = new Valeur2(v.getId(),v.getValeur(),v.getDate());
                         valeur.add(valeur2);
                     }
                    return valeur;
                }

            }
        }
        return valeur;
    }

    @RequestMapping(value = "/ExecuteCommands/{id}",method = RequestMethod.GET)
    public String executeCommande(@PathVariable Long id){
        Triger triger=trigerRepository.findById(id).get();
        Commande c = new Commande();
        if(triger!=null && triger.getCommandes().size()!=0){
            for(Commande commande:triger.getCommandes()) {

                if(!commande.isExecuted()) {
                    commande.setExecuted(true);
                    commandeRepository.save(commande);
                    c = commande;
                }

            }

        }
        return c.getValeur();

    }

}

@Data @AllArgsConstructor @NoArgsConstructor
class Valeur2{


    Long id;
    Double valeur;
    Date date;





}

