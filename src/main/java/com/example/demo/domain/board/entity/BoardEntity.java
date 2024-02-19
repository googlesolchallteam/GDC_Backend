package com.example.demo.domain.board.entity;

import com.example.demo.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "rental_table")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long boardId;

    @Column
    private String title;
    @Column
    private String contents;
    @Column
    private Long price;
    @Column
    private String gender;
    @Column
    private String clothCategory;
    @Column
    private String address;
    @Column
    private String position;

    @Column(name = "creation_time")
    private String currentTime;

    @Column(length = 2047)
    private String postImg;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "serverId")
    private MemberEntity memberEntity = new MemberEntity();

// 생성자, getter, setter 등 필요한 부분을 추가하세요
}
