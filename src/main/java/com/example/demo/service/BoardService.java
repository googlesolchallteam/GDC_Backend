package com.example.demo.service;

import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public void save(String boardData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        BoardEntity boardEntity = objectMapper.readValue(boardData, BoardEntity.class);
//        System.out.println(boardEntity);
        boardRepository.save(boardEntity);
    }
    public BoardEntity saveUpdate(String boardData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        BoardEntity boardEntity = objectMapper.readValue(boardData, BoardEntity.class);
//        System.out.println(boardEntity);
        boardRepository.save(boardEntity);
        return boardEntity;
    }
    public List<BoardEntity> search(String category) {
        return boardRepository.findBoardEntitiesByClothCategory(category);
    }

    public List<BoardEntity> findByServerId(Long serverId) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(serverId);
        return memberEntity.map(MemberEntity::getBoardEntities).orElse(null);
    }


    public Optional<BoardEntity> findByBoardId(Long boardId) {
        return boardRepository.findById(boardId);

    }

    public void delete(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
