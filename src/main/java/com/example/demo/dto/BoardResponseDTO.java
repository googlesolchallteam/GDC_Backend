package com.example.demo.dto;

import com.example.demo.entity.BoardEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class BoardResponseDTO {
    private Long boardId;
    private String title;
    private String contents;
    private Long price;
    private String gender;
    private String clothCategory;
    private String place;
    private List<String> postImg;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private String currentTime;
    private Long serverId;
    private String nickname;
    private String profileImage;
    public static BoardResponseDTO toDTO(BoardEntity boardEntity) throws JsonProcessingException {
        BoardResponseDTO boardResponseDTO = new BoardResponseDTO();
        boardResponseDTO.setBoardId(boardEntity.getBoardId());
        boardResponseDTO.setTitle(boardEntity.getTitle());
        boardResponseDTO.setContents(boardEntity.getContents());
        boardResponseDTO.setPrice(boardEntity.getPrice());
        boardResponseDTO.setGender(boardEntity.getGender());
        boardResponseDTO.setClothCategory(boardEntity.getClothCategory());
        boardResponseDTO.setPlace(boardEntity.getPlace());
        boardResponseDTO.setPostImg(toList(boardEntity.getPostImg()));
        boardResponseDTO.setServerId(boardEntity.getMemberEntity().getServerId());
        return boardResponseDTO;
    }

    public static List<String> toList(String postImgs) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(postImgs, new TypeReference<List<String>>() {});
    }
}
