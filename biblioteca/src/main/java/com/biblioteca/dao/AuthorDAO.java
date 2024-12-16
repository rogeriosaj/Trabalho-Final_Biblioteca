package com.biblioteca.dao;

import java.util.List;

import com.biblioteca.model.Author;

public interface AuthorDAO {
    Author findById(Long id);
    List<Author> findAll();
    void save(Author author);
    void delete(Long id);
}