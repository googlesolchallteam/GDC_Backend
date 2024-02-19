package com.example.demo.domain.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
@Getter
public class BoardData {
    private Long boardId;
    private String title;
    private String contents;
    private Long price;
    private String gender;
    private String clothCategory;
    private String address;
    private Map<String, String> position;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private String currentTime;

    private List<String> postImg;
    private Long serverId;
}