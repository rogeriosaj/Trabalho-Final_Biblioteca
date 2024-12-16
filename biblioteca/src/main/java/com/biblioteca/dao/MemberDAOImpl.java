package com.biblioteca.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biblioteca.model.Member;
import com.biblioteca.repository.MemberRepository;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
