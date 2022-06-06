package com.yhj.springtest01.service;

import com.yhj.springtest01.domain.Member;
import com.yhj.springtest01.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);  // 중복 회원 체크
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(mem -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
     * 회원ID로 특정 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
