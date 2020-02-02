package org.nuc.course.db.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.nuc.course.db.Mapper;
import org.nuc.course.dto.ArtDTO;
import org.nuc.course.dto.CommentDTO;
import org.nuc.course.model.Comment;

import java.util.List;

public interface CommentMapper extends Mapper<Comment> {
    
    List<CommentDTO> findAllDTO();

    List<CommentDTO> findByArtId(@Param("id") Long id);

    CommentDTO findIdDTO(@Param("id") Long id);


}