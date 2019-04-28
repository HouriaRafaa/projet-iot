package com.example.usersdemo.service;

import com.example.usersdemo.dao.CanalRepository;
import com.example.usersdemo.entities.AppUser;
import com.example.usersdemo.entities.Canal;
import com.example.usersdemo.entities.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CanalServiceImpl implements CanalService {


    @Autowired
    CanalRepository canalRepository;
    @Override
    public Canal saveCanal(String nom, String description , AppUser user, List<Field> fields) {
        Canal canal=new Canal();

        canal.setNom(nom);
        canal.setDescription(description);
        canal.setAppUser(user);
        canal.setFields(fields);
        canalRepository.save(canal);
        return canal;
    }

}
