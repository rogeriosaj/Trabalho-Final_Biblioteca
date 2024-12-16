package com.biblioteca.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private EntityManagerFactory emf;

    private DatabaseConnection() {
        emf = Persistence.createEntityManagerFactory("library_pu");
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
