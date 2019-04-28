package com.example.usersdemo.dao;


import com.example.usersdemo.entities.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;



public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
