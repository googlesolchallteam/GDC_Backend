package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "member_table")
public class MemberEntity {
    @Id
    private Long serverId;

    @OneToMany(mappedBy = "memberEntity")
    private List<BoardEntity> boardEntities = new ArrayList<>();

    @Column
    private String nickname;

    @Column(name = "profile_Image")
    private String profileImage;

}
