// package com.biblioteca.model;

// import javax.persistence.Entity; 
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;

// @Entity 
// public class Member { 
//     @Id 
//     @GeneratedValue(strategy = GenerationType.IDENTITY) 
//     private Long id; 
//     private String name; 

//     public Member() {} 

//     public Member(String name) { this.name = name; } 

//     public Long getId() { return id; } 

//     public String getName() { return name; }

//     public void setName(String name) { this.name = name; }
// }

package com.biblioteca.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Loan> loans;

    // Constructors, Getters, and Setters
    public Member() {}

    public Member(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Loan> getLoans() { return loans; }
    public void setLoans(List<Loan> loans) { this.loans = loans; }
}