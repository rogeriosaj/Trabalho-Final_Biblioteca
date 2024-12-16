package com.biblioteca.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biblioteca.model.ItemLoan;
import com.biblioteca.repository.ItemLoanRepository;

@Repository
public class ItemLoanDAOImpl implements ItemLoanDAO {
    @Autowired
    private ItemLoanRepository itemLoanRepository;

    @Override
    public ItemLoan findById(Long id) {
        return itemLoanRepository.findById(id).orElse(null);
    }

    @Override
    public List<ItemLoan> findAll() {
        return itemLoanRepository.findAll();
    }

    @Override
    public void save(ItemLoan itemLoan) {
        itemLoanRepository.save(itemLoan);
    }

    @Override
    public void delete(Long id) {
        itemLoanRepository.deleteById(id);
    }
}