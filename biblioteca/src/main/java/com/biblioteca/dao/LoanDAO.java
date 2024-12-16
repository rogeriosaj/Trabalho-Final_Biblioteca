package com.biblioteca.dao;

import java.util.List;

import com.biblioteca.model.Loan;
import com.biblioteca.model.Member;

public interface LoanDAO {
    Loan findById(Long id);
    List<Loan> findAll();
    void save(Loan loan);
    void delete(Long id);

    public List<Loan> findByMember(Member member);
}