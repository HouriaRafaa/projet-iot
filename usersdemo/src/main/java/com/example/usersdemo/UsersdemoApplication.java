package com.example.usersdemo;

import com.example.usersdemo.dao.AppUserRepository;
import com.example.usersdemo.dao.CanalRepository;
import com.example.usersdemo.dao.FieldRepository;
import com.example.usersdemo.entities.AppRole;
import com.example.usersdemo.entities.AppUser;
import com.example.usersdemo.entities.Canal;
import com.example.usersdemo.entities.Field;
import com.example.usersdemo.service.AccountService;
import com.pusher.rest.Pusher;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
public class UsersdemoApplication implements CommandLineRunner,RepositoryRestConfigurer {



    @Bean
    BCryptPasswordEncoder getBCPE(){
        return  new BCryptPasswordEncoder();
    }




    @Autowired
    CanalRepository canalRepository;

    @Autowired
    FieldRepository fieldRepository;

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;


    @Autowired
    AccountService accountService;

    private Pusher pusher;


    @Autowired
    AppUserRepository appUserRepository;

    public static void main(String[] args) {
        SpringApplication.run(UsersdemoApplication.class, args

        );

    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Canal.class,Field.class);

        repositoryRestConfiguration.getCorsRegistry()
                .addMapping("/**").
                allowedOrigins("*").
                allowedHeaders("*").
                allowedMethods("OPTIONS","HEAD","GET","POST","PUT","DELETE","PATCH");



//        accountService.saveRole(new AppRole(null,"USER"));
//        accountService.saveRole(new AppRole(null,"ADMIN"));
//
//
//       AppUser user1= accountService.saveUser("user1","1234","1234");
//       AppUser user2= accountService.saveUser("user2","1234","1234");
//
//        accountService.saveUser("admin","1234","1234");
//
//        accountService.addRoleToUser("user1","USER");
//
//        accountService.addRoleToUser("user2","USER");
//
//        accountService.addRoleToUser("admin","ADMIN");
//
//
//
//
//
//
//
////         String cle1= UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
////         String cle2 =UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
//     Canal c1=   canalRepository.save(new Canal(null,"canal1","Canal1 Description",new Date(),UUID.randomUUID().toString().replaceAll("-","").toUpperCase(),UUID.randomUUID().toString().replaceAll("-","").toUpperCase(),user1,null));
//
//    Canal c2=    canalRepository.save(new Canal(null,"canal2","Canal1 Description",new Date(),UUID.randomUUID().toString().replaceAll("-","").toUpperCase(),UUID.randomUUID().toString().replaceAll("-","").toUpperCase(),user2,null));
//
//     Canal c3=   canalRepository.save(new Canal(null,"canal55","Canal1 Description",new Date(),UUID.randomUUID().toString().replaceAll("-","").toUpperCase(),UUID.randomUUID().toString().replaceAll("-","").toUpperCase(),user2,null));
//
//
//        Field f1 = fieldRepository.save(new Field(null,"temperature",25,c1));
//
//        Field f2 = fieldRepository.save(new Field(null,"humidity",100,c2));
//
//        Collection<Field> fields= new ArrayList<>();
//        fields.add(f1);
//
//        Collection<Field> fields2= new ArrayList<>();
//        fields2.add(f2);


    }

}
