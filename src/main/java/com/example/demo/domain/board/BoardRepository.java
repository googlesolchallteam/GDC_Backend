package com.example.demo.domain.board;

import com.example.demo.domain.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

//    List<BoardEntity> findBoardEntitiesByClothCategory(String category);

    @Query("SELECT e FROM BoardEntity e WHERE (COALESCE(:address, '') = '' OR e.address = :address) AND " +
            "(COALESCE(:title, '') = '' OR e.title LIKE CONCAT('%', :title, '%')) AND " +
            "(COALESCE(:gender, '') = '' OR e.gender = :gender) AND " +
            "(COALESCE(:clothCategory, '') = '' OR e.clothCategory = :clothCategory)")
    List<BoardEntity> findWithParams(@Param("address") String address,
                                     @Param("title") String title,
                                     @Param("gender") String gender,
                                     @Param("clothCategory") String clothCategory);


}
