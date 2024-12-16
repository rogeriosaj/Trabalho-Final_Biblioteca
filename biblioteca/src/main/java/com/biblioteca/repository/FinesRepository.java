package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.Fines;

public interface FinesRepository extends JpaRepository<Fines, Long> {}