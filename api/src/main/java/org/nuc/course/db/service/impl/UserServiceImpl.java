package org.nuc.course.db.service.impl;

import org.nuc.course.db.dao.UserMapper;
import org.nuc.course.db.service.AbstractService;
import org.nuc.course.model.User;
import org.nuc.course.db.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xmmmmmovo on 2019/12/31.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
