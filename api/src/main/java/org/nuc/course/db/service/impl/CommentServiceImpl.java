package org.nuc.course.db.service.impl;

import org.nuc.course.db.dao.CommentMapper;
import org.nuc.course.db.service.AbstractService;
import org.nuc.course.db.service.CommentService;
import org.nuc.course.model.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xmmmmmovo on 2020/01/02.
 */
@Service
@Transactional
public class CommentServiceImpl extends AbstractService<Comment> implements CommentService {
    @Resource
    private CommentMapper commentMapper;

}
