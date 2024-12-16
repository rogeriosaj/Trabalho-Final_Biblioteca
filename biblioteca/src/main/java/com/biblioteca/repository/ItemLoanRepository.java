package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.ItemLoan;

public interface ItemLoanRepository extends JpaRepository<ItemLoan, Long> {}