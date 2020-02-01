package org.nuc.course.db.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.nuc.course.db.Mapper;
import org.nuc.course.dto.ArtDTO;
import org.nuc.course.model.Art;

import java.util.List;

public interface ArtMapper extends Mapper<Art> {

    List<ArtDTO> findAllDTO();

    List<ArtDTO> findUserDTO(@Param("id") Long id);


    ArtDTO findIdDTO(@Param("id") Long id);

    List<ArtDTO> findUserLikeDTO(@Param("name") String name);

    void updateCommentCount(@Param("id") Long id);

    void updateLikeCount(@Param("id") Long id);
}