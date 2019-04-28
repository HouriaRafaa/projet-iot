package com.example.usersdemo.web;


import com.example.usersdemo.dao.AppUserRepository;
import com.example.usersdemo.dao.ConfirmationTokenRepository;
import com.example.usersdemo.entities.AppUser;
import com.example.usersdemo.entities.ConfirmationToken;
import com.example.usersdemo.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private AppUserRepository userRepository;


    @PostMapping("/register")
    public AppUser register(@RequestBody UserForm userForm)
    {
        return accountService.saveUser(userForm.getUsername(),userForm.getEmail(),userForm.getPassword(),userForm.getPasswordConfirmed());
    }


    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            AppUser user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setActived(true);
            userRepository.save(user);

        }

        return "Compte Verifié ^__^";
    }


    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseBody
    public Long currentUserName(Authentication authentication) {


        AppUser user= accountService.loadUserByEmail(authentication.getName());
        return user.getId();
    }

}

@Data @NoArgsConstructor @AllArgsConstructor
class UserForm{
    private String username;
    private String email;
    private  String password;
    private String passwordConfirmed;

}


