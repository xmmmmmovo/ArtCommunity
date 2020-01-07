package org.nuc.course.db.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.nuc.course.db.Mapper;
import org.nuc.course.dto.ArtDTO;
import org.nuc.course.dto.LikeDTO;
import org.nuc.course.model.LikeList;

import java.util.List;

public interface LikeListMapper extends Mapper<LikeList> {
    @Select("select like_list.id,\n" +
            "       user_id as userId,\n" +
            "       art_pic_url as artPicUrl,\n" +
            "       tag_name as tagName,\n" +
            "       art_id as artId,\n" +
            "       art_name as artName,\n" +
            "       like_time as likeTime\n" +
            "from like_list\n" +
            "left join user u on like_list.user_id = u.id\n" +
            "left join art a on like_list.art_id = a.id\n" +
            "left join tag t on a.art_tag = t.id")
    List<LikeDTO> findAllDTO();

    @Select("select like_list.id,\n" +
            "       user_id as userId,\n" +
            "       art_pic_url as artPicUrl,\n" +
            "       tag_name as tagName,\n" +
            "       art_id as artId,\n" +
            "       art_name as artName,\n" +
            "       like_time as likeTime\n" +
            "from like_list\n" +
            "left join user u on like_list.user_id = u.id\n" +
            "left join art a on like_list.art_id = a.id\n" +
            "left join tag t on a.art_tag = t.id\n" +
            "where token = '${token}'")
    List<LikeDTO> findLikeListDTO(@Param("token") String token);

    @Select("select like_list.id,\n" +
            "       user_id as userId,\n" +
            "       art_pic_url as artPicUrl,\n" +
            "       tag_name as tagName,\n" +
            "       art_id as artId,\n" +
            "       art_name as artName,\n" +
            "       like_time as likeTime\n" +
            "from like_list\n" +
            "left join user u on like_list.user_id = u.id\n" +
            "left join art a on like_list.art_id = a.id\n" +
            "left join tag t on a.art_tag = t.id\n" +
            "where like_list.id = ${id}")
    LikeDTO findIdDTO(@Param("id") Long id);

}