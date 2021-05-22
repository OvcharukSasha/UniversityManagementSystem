package com.univer;


import com.univer.repos.StudentRepository;
import com.univer.repos.UserRepository;
import com.univer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class UnivirApplication implements CommandLineRunner{ 
    @Autowired UserRepository studentLoginRepositoty;
    @Autowired StudentRepository studentRepository;
    @Autowired StudentService service;
	public static void main(String[] args) {
		SpringApplication.run(UnivirApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        studentLoginRepositoty.findAll().forEach(u->service.updatePassword(u.getLogin(),"12345"));
        
    }

}
