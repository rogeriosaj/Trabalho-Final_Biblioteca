package com.biblioteca.dao;

import java.util.List;

import com.biblioteca.model.Member;

public interface MemberDAO {
    Member findById(Long id);
    List<Member> findAll();
    void save(Member member);
    void delete(Long id);

    public void deleteById(Long memberId);
}
