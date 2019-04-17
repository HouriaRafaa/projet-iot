package com.example.usersdemo.web;


import com.example.usersdemo.entities.AppUser;
import com.example.usersdemo.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm)
    {
        return accountService.saveUser(userForm.getUsername(),userForm.getPassword(),userForm.getPasswordConfirmed());
    }



    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseBody
    public Long currentUserName(Authentication authentication) {


        AppUser user= accountService.loadUserByUsername(authentication.getName());
        return user.getId();
    }

}

@Data
class UserForm{
    private String username;
    private  String password;
    private String passwordConfirmed;




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmed() {
        return passwordConfirmed;
    }

    public void setPasswordConfirmed(String passwordConfirmed) {
        this.passwordConfirmed = passwordConfirmed;
    }
}


