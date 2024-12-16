package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {}