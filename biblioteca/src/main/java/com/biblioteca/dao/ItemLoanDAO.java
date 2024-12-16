package com.biblioteca.dao;

import java.util.List;

import com.biblioteca.model.ItemLoan;

public interface ItemLoanDAO {
    ItemLoan findById(Long id);
    List<ItemLoan> findAll();
    void save(ItemLoan itemLoan);
    void delete(Long id);
}