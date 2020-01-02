package org.nuc.course.db.service.impl;

import org.nuc.course.db.dao.SizeMapper;
import org.nuc.course.db.service.AbstractService;
import org.nuc.course.db.service.SizeService;
import org.nuc.course.model.Size;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xmmmmmovo on 2020/01/02.
 */
@Service
@Transactional
public class SizeServiceImpl extends AbstractService<Size> implements SizeService {
    @Resource
    private SizeMapper sizeMapper;

}
