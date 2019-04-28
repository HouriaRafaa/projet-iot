package com.example.usersdemo.web;


import com.example.usersdemo.dao.CanalRepository;
import com.example.usersdemo.dao.FieldRepository;
import com.example.usersdemo.entities.AppUser;
import com.example.usersdemo.entities.Canal;
import com.example.usersdemo.entities.Field;
import com.example.usersdemo.service.AccountService;
import com.example.usersdemo.service.CanalService;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class CanalController {

    @Autowired
    CanalRepository canalRepository;

    @Autowired
    CanalService canalService;

    @Autowired
    FieldRepository fieldRepository;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/canals", method = RequestMethod.POST)
    public void create(@RequestBody Map<String, Object> payload) throws Exception {

        Canal canal;
        AppUser appUser=accountService.loadUserById(Long.parseLong(String.valueOf(payload.get("userId"))));


        canal= canalService.saveCanal(
                payload.get("nom").toString(),
                payload.get("description").toString(),
                appUser,
                null
        );


        canal.setCleEcriture(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
        canal.setCleLecture(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
        canal.setDateCreation(new Date());
        canalRepository.save(canal);

        payload.forEach((s, o) -> {
            if (s.contains("field")){
                fieldRepository.save(new Field(o.toString(), canal, null));
            }
        });


    }

}

