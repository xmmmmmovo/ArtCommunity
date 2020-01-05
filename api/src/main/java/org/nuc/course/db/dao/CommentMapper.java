package org.nuc.course.db.dao;

import org.apache.ibatis.annotations.Select;
import org.nuc.course.db.Mapper;
import org.nuc.course.dto.ArtDTO;
import org.nuc.course.dto.CommentDTO;
import org.nuc.course.model.Comment;

import java.util.List;

public interface CommentMapper extends Mapper<Comment> {
    @Select("")
    List<CommentDTO> findAllDTO();
}