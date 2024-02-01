package com.example.demo.repository;

import com.example.demo.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findBoardEntitiesByClothCategory(String category);

    Optional<BoardEntity> findByBoardID(Long boardId);

}
