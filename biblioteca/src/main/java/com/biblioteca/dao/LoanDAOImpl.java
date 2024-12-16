package com.biblioteca.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biblioteca.model.Loan;
import com.biblioteca.model.Member;
import com.biblioteca.repository.LoanRepository;

@Repository
public class LoanDAOImpl implements LoanDAO {
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public Loan findById(Long id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Override
    public List<Loan> findByMember(Member member) {
        return loanRepository.findByMember(member);
    }

    @Override
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Override
    public void save(Loan loan) {
        loanRepository.save(loan);
    }

    @Override
    public void delete(Long id) {
        loanRepository.deleteById(id);
    }
}