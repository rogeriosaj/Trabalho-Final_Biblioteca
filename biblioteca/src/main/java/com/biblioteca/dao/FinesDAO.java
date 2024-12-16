package com.biblioteca.dao;

import java.util.List;

import com.biblioteca.model.Fines;

public interface FinesDAO {
    Fines findById(Long id);
    List<Fines> findAll();
    void save(Fines fines);
    void delete(Long id);
}