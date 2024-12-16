package com.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.Loan;
import com.biblioteca.model.Member;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    public List<Loan> findAllByMember(Member member);

    public List<Loan> findByMember(Member member);
}