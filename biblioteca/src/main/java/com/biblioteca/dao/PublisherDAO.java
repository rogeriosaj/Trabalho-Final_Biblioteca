package com.biblioteca.dao;

import java.util.List;

import com.biblioteca.model.Publisher;

public interface PublisherDAO {
    Publisher findById(Long id);
    List<Publisher> findAll();
    void save(Publisher publisher);
    void delete(Long id);
}