package org.sid;

import org.sid.entites.AppRole;
import org.sid.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class SecServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecServiceApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
 //   @Bean
    CommandLineRunner start(AccountService accountService){
       return args -> {
           accountService.save(new AppRole(null,"USER"));
           accountService.save(new AppRole(null,"ADMIN"));
           Stream.of("user1","user2","user3","admin").forEach(u->{
               accountService.saveUser(u,"1234","1234");
           });

           accountService.addRoleToUser("admin","ADMIN");

       };
    }

}