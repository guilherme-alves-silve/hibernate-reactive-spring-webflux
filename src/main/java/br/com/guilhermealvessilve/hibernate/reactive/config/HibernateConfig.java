package br.com.guilhermealvessilve.hibernate.reactive.config;

import org.hibernate.reactive.mutiny.Mutiny;
import org.hibernate.reactive.stage.Stage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class HibernateConfig {

    @Primary
    @Bean(name = "reactive-pu")
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("ReactivePU");
    }

    @Bean
    public Mutiny.SessionFactory reactiveSessionFactory(final EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.unwrap(Mutiny.SessionFactory.class);
    }

    @Bean
    public Stage.SessionFactory futureSessionFactory(final EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.unwrap(Stage.SessionFactory.class);
    }

    private static class Wrapper {
        EntityManagerFactory entityManagerFactory;
    }
}
