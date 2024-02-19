package com.example.demo.domain.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SearchParam {
    private String place;
    private String title;
    private String gender;
    private String clothCategory;
}
