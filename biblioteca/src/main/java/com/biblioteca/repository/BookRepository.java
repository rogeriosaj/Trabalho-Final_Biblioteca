// package com.biblioteca.repository;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.biblioteca.model.Book; 
// import com.biblioteca.model.Member;

// public interface BookRepository extends JpaRepository<Book, Long> { 
//     List<Book> findByTitle(String title); 
//     List<Book> findByIssuedTo(Member member);
// }

package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}