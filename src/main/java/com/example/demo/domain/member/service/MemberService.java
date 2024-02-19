package com.example.demo.domain.member.service;

import com.example.demo.domain.member.MemberRepository;
import com.example.demo.domain.member.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Boolean isMember(Long serverId) {
        Optional<MemberEntity> memberRepositoryById = memberRepository.findById(serverId);
        return memberRepositoryById.isPresent();
    }
}
