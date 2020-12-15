package br.com.guilhermealvessilve.hibernate.reactive.controller;

import br.com.guilhermealvessilve.hibernate.reactive.dao.PersonDAO;
import br.com.guilhermealvessilve.hibernate.reactive.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PersonController {

    @Autowired
    private PersonDAO dao;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot and Hibernate Reactive! Person: " + dao.getPerson(1L);
    }

    @RequestMapping("/mono")
    public Mono<Person> mono() {
        return dao.findMonoPerson(1L);
    }

}
