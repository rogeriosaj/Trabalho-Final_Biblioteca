package com.biblioteca.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biblioteca.model.Fines;
import com.biblioteca.repository.FinesRepository;

@Repository
public class FinesDAOImpl implements FinesDAO {
    @Autowired
    private FinesRepository finesRepository;

    @Override
    public Fines findById(Long id) {
        return finesRepository.findById(id).orElse(null);
    }

    @Override
    public List<Fines> findAll() {
        return finesRepository.findAll();
    }

    @Override
    public void save(Fines fines) {
        finesRepository.save(fines);
    }

    @Override
    public void delete(Long id) {
        finesRepository.deleteById(id);
    }
}