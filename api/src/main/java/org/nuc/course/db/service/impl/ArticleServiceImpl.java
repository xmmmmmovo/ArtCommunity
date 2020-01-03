package org.nuc.course.db.service.impl;

import org.nuc.course.db.dao.ArticleMapper;
import org.nuc.course.model.Article;
import org.nuc.course.db.service.ArticleService;
import org.nuc.course.db.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xmmmmmovo on 2020/01/03.
 */
@Service
@Transactional
public class ArticleServiceImpl extends AbstractService<Article> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

}