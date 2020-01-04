package org.nuc.course.db.service.impl;

import org.nuc.course.db.dao.TagMapper;
import org.nuc.course.model.Tag;
import org.nuc.course.db.service.TagService;
import org.nuc.course.db.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xmmmmmovo on 2020/01/04.
 */
@Service
@Transactional
public class TagServiceImpl extends AbstractService<Tag> implements TagService {
    @Resource
    private TagMapper tagMapper;

}
