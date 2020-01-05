package org.nuc.course.db.service.impl;

import org.nuc.course.db.dao.CommentMapper;
import org.nuc.course.dto.CommentDTO;
import org.nuc.course.model.Comment;
import org.nuc.course.db.service.CommentService;
import org.nuc.course.db.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by xmmmmmovo on 2020/01/04.
 */
@Service
@Transactional
public class CommentServiceImpl extends AbstractService<Comment> implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<CommentDTO> findAllList() {
        return commentMapper.findAllDTO();
    }

    @Override
    public CommentDTO findADTO(Long id) {
        return commentMapper.findIdDTO(id);
    }
}