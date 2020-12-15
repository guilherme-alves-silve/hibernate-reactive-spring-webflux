package br.com.guilhermealvessilve.hibernate.reactive;

import br.com.guilhermealvessilve.hibernate.reactive.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.transaction.Transactional;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("br.com.guilhermealvessilve.hibernate.reactive")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private PersonDAO dao;

    @Bean
    @Transactional
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            dao.savePerson("Guilherme");
            System.out.println("Spring Boot 2.0 working with Hibernate 5.x! ");
        };
    }
}