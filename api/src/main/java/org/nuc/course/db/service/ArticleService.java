package org.nuc.course.db.service;
import org.nuc.course.dto.ArticleDTO;
import org.nuc.course.model.Article;
import org.nuc.course.db.service.Service;

import java.util.List;


/**
 * Created by xmmmmmovo on 2020/01/04.
 */
public interface ArticleService extends Service<Article> {
    List<ArticleDTO> findAllList();
    List<ArticleDTO> findArticleDTO(String token);
    ArticleDTO findADTO(Long id);
}
