package org.nuc.course.db.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.nuc.course.db.Mapper;
import org.nuc.course.dto.ArtDTO;
import org.nuc.course.dto.CommentDTO;
import org.nuc.course.model.Comment;

import java.util.List;

public interface CommentMapper extends Mapper<Comment> {
    @Select("select comment.id,\n" +
            "       comment_by as commentBy,\n" +
            "       user_name as userName,\n" +
            "       comment_content as commentContent,\n" +
            "       parent_id as parentId,\n" +
            "       a.id as artId,\n" +
            "       art_name as artName,\n" +
            "       comment_time as commentTime,\n" +
            "       comment_like_count as commentLikeCount\n" +
            "from comment\n" +
            "    left join user u on comment.comment_by = u.id\n" +
            "    left join art a on comment.parent_id = a.id")
    List<CommentDTO> findAllDTO();

    @Select("select comment.id,\n" +
            "       comment_by as commentBy,\n" +
            "       user_name as userName,\n" +
            "       comment_content as commentContent,\n" +
            "       parent_id as parentId,\n" +
            "       a.id as artId,\n" +
            "       art_name as artName,\n" +
            "       comment_time as commentTime,\n" +
            "       comment_like_count as commentLikeCount\n" +
            "from comment\n" +
            "    left join user u on comment.comment_by = u.id\n" +
            "    left join art a on comment.parent_id = a.id\n" +
            "where comment.id = ${id}")
    CommentDTO findIdDTO(@Param("id") Long id);
}