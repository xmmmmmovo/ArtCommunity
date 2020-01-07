package org.nuc.course.db.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.nuc.course.db.Mapper;
import org.nuc.course.dto.ArticleDTO;
import org.nuc.course.model.Article;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {

    @Select("select article.id,\n" +
            "       article_author as articleAuthor,\n" +
            "       article_title as articleTitle,\n" +
            "       article_content as articleContent,\n" +
            "       article_front as articleFront,\n" +
            "       article_author as articleAuthor,\n" +
            "       create_time as createTime,\n" +
            "       user_name as userName\n" +
            "from article left join user u on article.article_author = u.id")
    List<ArticleDTO> findAllDTO();

    @Select("select article.id,\n" +
            "       article_author as articleAuthor,\n" +
            "       article_title as articleTitle,\n" +
            "       article_content as articleContent,\n" +
            "       article_front as articleFront,\n" +
            "       article_author as articleAuthor,\n" +
            "       create_time as createTime,\n" +
            "       user_name as userName\n" +
            "from article left join user u on article.article_author = u.id\n" +
            "where token = '${token}'")
    List<ArticleDTO> findArticleDTO(@Param("token") String token);

    @Select("select article.id,\n" +
            "       article_author as articleAuthor,\n" +
            "       article_title as articleTitle,\n" +
            "       article_content as articleContent,\n" +
            "       article_front as articleFront,\n" +
            "       article_author as articleAuthor,\n" +
            "       create_time as createTime,\n" +
            "       user_name as userName\n" +
            "from article left join user u on article.article_author = u.id\n" +
            "where article.id = ${id}")
    ArticleDTO findIdDTO(@Param("id") Long id);
}