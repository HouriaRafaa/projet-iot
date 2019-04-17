package com.example.usersdemo.service;

import com.example.usersdemo.entities.AppRole;
import com.example.usersdemo.entities.AppUser;

public interface AccountService
{


    public AppUser saveUser(String username,String password,String confirmedPassword);

    public AppRole saveRole(AppRole role);

    public AppUser loadUserByUsername(String username);

    public AppUser loadUserById(Long id);

    public  void addRoleToUser(String username,String rolename);
}
