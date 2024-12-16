package com.biblioteca.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "loan")
    private List<ItemLoan> items;

    private LocalDate issueDate;
    private LocalDate dueDate;

    // Constructors, Getters, and Setters
    public Loan() {}

    public Loan(Member member, LocalDate issueDate, LocalDate dueDate) {
        this.member = member;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public Long getId() { return id; }
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    public List<ItemLoan> getItems() { return items; }
    public void setItems(List<ItemLoan> items) { this.items = items; }
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}