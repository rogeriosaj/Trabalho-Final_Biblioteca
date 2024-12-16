// package com.biblioteca.model;

// import java.time.LocalDate;

// import javax.persistence.Entity; 
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;

// @Entity 
// public class Book { 
//     @Id 
//     @GeneratedValue(strategy = GenerationType.IDENTITY) 
//     private Long id; 
//     private String title; 
//     private String author; 
//     private boolean isAvailable;
    
//     private LocalDate issueDate; 
//     private LocalDate dueDate;

//     @ManyToOne
//     @JoinColumn(name = "member_id")
//     private Member issuedTo;
    
//     public Book() {} 
    
//     public Book(String title, String author) { 
//         this.title = title; 
//         this.author = author; 
//         this.isAvailable = true; 
//     } 
    
//     public Long getId() { return id; } 

//     public String getTitle() { return title; } 

//     public void setTitle(String title) { 
//         this.title = title; 
//     } 
    
//     public String getAuthor() { return author; } 
    
//     public void setAuthor(String author) { 
//         this.author = author; 
//     } 

//     public boolean isAvailable() { return isAvailable; } 
    
//     public void setAvailable(boolean isAvailable) { 
//         this.isAvailable = isAvailable; 
//     } 

//     public LocalDate getIssueDate() { 
//         return issueDate; 
//     } 
//     public void setIssueDate(LocalDate issueDate) {
//         this.issueDate = issueDate; 
//     } 
    
//     public LocalDate getDueDate() { 
//         return dueDate; 
//     } 
    
//     public void setDueDate(LocalDate dueDate) { 
//         this.dueDate = dueDate; 
//     }

//     public Member getIssuedTo() { 
//         return issuedTo; 
//     }

//     public void setIssuedTo(Member issuedTo) { 
//         this.issuedTo = issuedTo; 
//     }
// }

package com.biblioteca.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    
    @ManyToOne
    private Author author;

    @ManyToOne
    private Publisher publisher;
    
    private boolean isAvailable;
    private LocalDate issueDate;
    private LocalDate dueDate;

    @ManyToOne
    private Member issuedTo;

    // Constructors, Getters, and Setters
    public Book() {}

    public Book(String title, Author author, Publisher publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isAvailable = true;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
    public Publisher getPublisher() { return publisher; }
    public void setPublisher(Publisher publisher) { this.publisher = publisher; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Member getIssuedTo() { return issuedTo; }
    public void setIssuedTo(Member issuedTo) { this.issuedTo = issuedTo; }
}