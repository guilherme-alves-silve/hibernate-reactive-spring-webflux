package br.com.guilhermealvessilve.hibernate.reactive.dao;

import br.com.guilhermealvessilve.hibernate.reactive.model.Person;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class PersonDAO {

    @Autowired
    private Mutiny.SessionFactory sessionFactory;

    public void savePerson(String name) {
        final var person = new Person();
        person.setName(name);

        sessionFactory.withTransaction((session, tx) ->
            session.persist(person)
                    .onFailure(throwable -> {
                        tx.markForRollback();
                        return true;
                    })
                    .invoke(() -> System.out.println("Record Successfully Inserted In The Database"))
        ).await()
        .indefinitely();
    }

    public Person getPerson(Long id) {
        final var session = sessionFactory.openSession();

        return session.find(Person.class, id)
        .invoke(person -> {
            if (person != null) {
                System.out.println(person.getName() + " is a nice person!");
            }
        }).call(() ->
            session.createQuery("SELECT id, name FROM person ORDER BY name DESC")
                    .getResultList()
                    .invoke(list -> list.forEach(System.out::println))
        ).call(() -> session.find(Person.class, id))
        .eventually(session::close)
        .await()
        .indefinitely();
    }

    public Mono<Person> findMonoPerson(Long id) {
        Mutiny.Session session = sessionFactory.openSession();

        try {
            final var future = session.find(Person.class, id)
                    .eventually(session::close)
                    .subscribe()
                    .asCompletionStage();
            return Mono.fromFuture(future);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}