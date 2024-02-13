package com.example.demo.service;

import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
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
