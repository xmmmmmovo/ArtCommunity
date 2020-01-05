package org.nuc.course.db.dao;

import org.apache.ibatis.annotations.Select;
import org.nuc.course.db.Mapper;
import org.nuc.course.dto.ArticleDTO;
import org.nuc.course.model.Article;

public interface ArticleMapper extends Mapper<Article> {

    @Select("select article.id, article_author, article_title, article_content, article_front, create_time, user_name\n" +
            "from article left join user u on article.article_author = u.id")
    ArticleDTO findAllDTO();
}