package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {}