// package com.biblioteca.repository;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.biblioteca.model.Member; 
// public interface MemberRepository extends JpaRepository<Member, Long> {
//     Optional<Member> findById(Long id); 
// }

package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {}