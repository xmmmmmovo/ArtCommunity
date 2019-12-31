package org.nuc.course.db.service.impl;

import org.nuc.course.db.dao.AdminMapper;
import org.nuc.course.db.service.AbstractService;
import org.nuc.course.db.service.AdminService;
import org.nuc.course.model.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xmmmmmovo on 2019/12/31.
 */
@Service
@Transactional
public class AdminServiceImpl extends AbstractService<Admin> implements AdminService {
    @Resource
    private AdminMapper adminMapper;
}
