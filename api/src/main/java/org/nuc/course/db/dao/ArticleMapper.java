package org.nuc.course.db.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.nuc.course.db.Mapper;
import org.nuc.course.dto.ArticleDTO;
import org.nuc.course.model.Article;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {

    List<ArticleDTO> findAllDTO();

    List<ArticleDTO> findArticleDTO(@Param("id") Long id);

    ArticleDTO findIdDTO(@Param("id") Long id);
}