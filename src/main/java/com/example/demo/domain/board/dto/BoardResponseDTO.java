package com.example.demo.domain.board.dto;

import com.example.demo.domain.board.entity.BoardEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

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
    private String address;

    private List<String> postImg;
    private Map<String, Double> position;
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
        boardResponseDTO.setAddress(boardEntity.getAddress());
        boardResponseDTO.setPostImg(toList(boardEntity.getPostImg()));
        boardResponseDTO.setServerId(boardEntity.getMemberEntity().getServerId());
        boardResponseDTO.setPosition(toMap(boardEntity.getPosition()));
        return boardResponseDTO;
    }

    public static List<String> toList(String postImgs) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(postImgs, new TypeReference<List<String>>() {});
    }
    public static Map<String, Double> toMap(String postImgs) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(postImgs, new TypeReference<Map<String, Double>>() {});
    }
}
