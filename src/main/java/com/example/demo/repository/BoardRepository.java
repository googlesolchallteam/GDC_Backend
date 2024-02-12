package com.example.demo.repository;

import com.example.demo.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

//    List<BoardEntity> findBoardEntitiesByClothCategory(String category);

    @Query("SELECT e FROM BoardEntity e WHERE (COALESCE(:place, '') = '' OR e.place = :place) AND " +
            "(COALESCE(:title, '') = '' OR e.title LIKE CONCAT('%', :title, '%')) AND " +
            "(COALESCE(:gender, '') = '' OR e.gender = :gender) AND " +
            "(COALESCE(:clothCategory, '') = '' OR e.clothCategory = :clothCategory)")
    List<BoardEntity> findWithParams(@Param("place") String place,
                                     @Param("title") String title,
                                     @Param("gender") String gender,
                                     @Param("clothCategory") String clothCategory);

}
