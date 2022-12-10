package com.example.testprac;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class TestPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestPracApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(StuRepository stuRepository){
//        return args -> {
//            stuRepository.save(new Users(null, "Name"));
//            stuRepository.save(new Users(null, "Name"));
//            stuRepository.save(new Users(null, "Name"));
//            stuRepository.findAll().forEach(p->{
//                System.out.println(p.getName());
//            });
//        };
//    }

}
