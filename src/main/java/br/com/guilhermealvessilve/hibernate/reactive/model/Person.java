package br.com.guilhermealvessilve.hibernate.reactive.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.StringJoiner;

@Entity
@NamedQuery(name="Person.findAll", query="SELECT t FROM Person t")
@XmlRootElement
public class Person {

    private Long id;

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
