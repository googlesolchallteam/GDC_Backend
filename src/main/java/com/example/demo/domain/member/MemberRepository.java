package com.example.demo.domain.member;

import com.example.demo.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    @Override
    Optional<MemberEntity> findById(Long serverId);
}
