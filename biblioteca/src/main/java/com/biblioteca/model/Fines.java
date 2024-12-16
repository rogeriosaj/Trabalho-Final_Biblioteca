package com.biblioteca.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Fines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Book book;

    private long amount;
    private LocalDate dateIssued;

    // Constructors, Getters, and Setters
    public Fines() {}

    public Fines(Member member, Book book, long amount, LocalDate dateIssued) {
        this.member = member;
        this.book = book;
        this.amount = amount;
        this.dateIssued = dateIssued;
    }

    public Long getId() { return id; }
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    public long getAmount() { return amount; }
    public void setAmount(long amount) { this.amount = amount; }
    public LocalDate getDateIssued() { return dateIssued; }
    public void setDateIssued(LocalDate dateIssued) { this.dateIssued = dateIssued; }
}