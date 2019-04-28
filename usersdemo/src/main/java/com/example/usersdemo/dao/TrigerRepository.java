package com.example.usersdemo.dao;

import com.example.usersdemo.entities.Triger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrigerRepository extends JpaRepository<Triger,Long> {

    public Triger findTrigerByApikey(String apikey);
}
