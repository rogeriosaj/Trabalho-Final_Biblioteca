package com.biblioteca.dao;

import java.util.List;

import com.biblioteca.model.Book;

public interface BookDAO {
    Book findById(Long id);
    List<Book> findAll();
    void save(Book book);
    void delete(Long id);

    public void deleteById(Long bookId);
}
