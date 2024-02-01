package com.example.demo.entity;

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
    private Long boardID;

    private String title;

    private String contents;

    private Long price;

    private String gender;

    private String clothCategory;

    private String place;

    @Column(name = "creation_time")
    private String currentTime;

    private String postImg;

    @ManyToOne
    @JoinColumn(referencedColumnName = "serverId")
    private MemberEntity memberEntity = new MemberEntity();

// 생성자, getter, setter 등 필요한 부분을 추가하세요
}
