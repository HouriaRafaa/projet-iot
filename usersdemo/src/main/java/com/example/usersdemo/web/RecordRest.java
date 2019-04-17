package com.example.usersdemo.web;

import com.example.usersdemo.dao.CanalRepository;
import com.example.usersdemo.dao.FieldRepository;
import com.example.usersdemo.dao.ValeurRepository;
import com.example.usersdemo.entities.Canal;
import com.example.usersdemo.entities.Field;
import com.example.usersdemo.entities.Valeur;
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

    @GetMapping("/record")
    @ResponseBody
    public String updateCanal(@RequestParam Map<String,String> allParams) {

       Canal canal = canalRepository.findCanalByCleEcriture(allParams.get("key"));

        Pusher pusher = new Pusher("762880", "84bee67aad46ed497369", "5017a5ee0387085255ae");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);

        System.out.println("pusher ::::::::" + pusher.toString());

        pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", "hello world"));


      //  pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", "hello world"));

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

//    @GetMapping("/record")
//    @ResponseBody
//    public String get(){
//        return "value recorded";
//    }

}

@Data @AllArgsConstructor @NoArgsConstructor
class Valeur2{


    Long id;
    Double valeur;
    Date date;





}

