package org.nuc.course.db.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.nuc.course.db.Mapper;
import org.nuc.course.dto.ArtDTO;
import org.nuc.course.dto.LikeDTO;
import org.nuc.course.model.LikeList;

import java.util.List;

public interface LikeListMapper extends Mapper<LikeList> {

    List<LikeDTO> findAllDTO();

    List<LikeDTO> findLikeListDTO(@Param("id") Long id);

    LikeDTO findIdDTO(@Param("id") Long id);

    List<LikeDTO> findLikeDTO(@Param("user_id") Long userId, @Param("art_id") Long artId);
}