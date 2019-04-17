package com.example.usersdemo.service;

import com.example.usersdemo.entities.AppUser;
import com.example.usersdemo.entities.Canal;
import com.example.usersdemo.entities.Field;

import java.util.Date;
import java.util.List;

public interface CanalService {


     Canal saveCanal(String nom, String description, AppUser user, List<Field> fields);




}
