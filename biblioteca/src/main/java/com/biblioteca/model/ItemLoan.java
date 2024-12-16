package com.biblioteca.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Loan loan;

    @ManyToOne
    private Book book;

    // Constructors, Getters, and Setters
    public ItemLoan() {}

    public ItemLoan(Loan loan, Book book) {
        this.loan = loan;
        this.book = book;
    }

    public Long getId() { return id; }
    public Loan getLoan() { return loan; }
    public void setLoan(Loan loan) { this.loan = loan; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
}