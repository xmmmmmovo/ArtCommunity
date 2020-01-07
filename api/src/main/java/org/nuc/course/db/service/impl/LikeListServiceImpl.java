package org.nuc.course.db.service.impl;

import org.nuc.course.db.dao.LikeListMapper;
import org.nuc.course.model.LikeList;
import org.nuc.course.db.service.LikeListService;
import org.nuc.course.db.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xmmmmmovo on 2020/01/07.
 */
@Service
@Transactional
public class LikeListServiceImpl extends AbstractService<LikeList> implements LikeListService {
    @Resource
    private LikeListMapper likeListMapper;

}
