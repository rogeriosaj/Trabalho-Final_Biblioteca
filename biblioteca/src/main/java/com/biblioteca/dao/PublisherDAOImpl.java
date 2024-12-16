package com.biblioteca.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biblioteca.model.Publisher;
import com.biblioteca.repository.PublisherRepository;

@Repository
public class PublisherDAOImpl implements PublisherDAO {
    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public Publisher findById(Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    @Override
    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }
}