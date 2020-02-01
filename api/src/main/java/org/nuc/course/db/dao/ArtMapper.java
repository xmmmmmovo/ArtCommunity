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

    @Select("select\n" +
            "       art.id,\n" +
            "       art_name as artName,\n" +
            "       create_time as createTime,\n" +
            "       modified_time as modifiedTime,\n" +
            "       art_author as artAuthor,\n" +
            "       user_name as userName,\n" +
            "       art_size as artSize,\n" +
            "       length,\n" +
            "       height,\n" +
            "       art_tag as artTag,\n" +
            "       tag_name as tagName,\n" +
            "       art_like_num as artLikeNum,\n" +
            "       art_comment_num as artCommentNum,\n" +
            "       art_content as artContent,\n" +
            "       art_pic_url as artPicUrl\n" +
            "from art\n" +
            "    left join tag t on art.art_tag = t.id\n" +
            "    left join size s on art.art_size = s.id\n" +
            "    left join user u on art.art_author = u.id\n" +
            "where art.id = ${id}")
    ArtDTO findIdDTO(@Param("id") Long id);

    @Select("select\n" +
            "       art.id,\n" +
            "       art_name as artName,\n" +
            "       create_time as createTime,\n" +
            "       modified_time as modifiedTime,\n" +
            "       art_author as artAuthor,\n" +
            "       user_name as userName,\n" +
            "       art_size as artSize,\n" +
            "       length,\n" +
            "       height,\n" +
            "       art_tag as artTag,\n" +
            "       tag_name as tagName,\n" +
            "       art_like_num as artLikeNum,\n" +
            "       art_comment_num as artCommentNum,\n" +
            "       art_content as artContent,\n" +
            "       art_pic_url as artPicUrl\n" +
            "from art\n" +
            "    left join tag t on art.art_tag = t.id\n" +
            "    left join size s on art.art_size = s.id\n" +
            "    left join user u on art.art_author = u.id\n" +
            "where art.art_name like '%${name}%'")
    List<ArtDTO> findUserLikeDTO(@Param("name") String name);

    @Update("update art set art_comment_num = art_comment_num + 1 where id = ${id}")
    void updateCommentCount(@Param("id") Long id);

    @Update("update art set art_like_num = art_like_num + 1 where id = ${id}")
    void updateLikeCount(@Param("id") Long id);
}