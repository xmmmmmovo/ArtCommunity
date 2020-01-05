package org.nuc.course.db.dao;

import org.apache.ibatis.annotations.Select;
import org.nuc.course.db.Mapper;
import org.nuc.course.dto.ArtDTO;
import org.nuc.course.model.Art;

import java.util.List;

public interface ArtMapper extends Mapper<Art> {
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
            "    left join user u on art.art_author = u.id")
    List<ArtDTO> findAllDTO();
}