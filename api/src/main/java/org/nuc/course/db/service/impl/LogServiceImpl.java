package org.nuc.course.db.service.impl;

import org.nuc.course.db.dao.LogMapper;
import org.nuc.course.model.Log;
import org.nuc.course.db.service.LogService;
import org.nuc.course.db.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xmmmmmovo on 2020/01/04.
 */
@Service
@Transactional
public class LogServiceImpl extends AbstractService<Log> implements LogService {
    @Resource
    private LogMapper logMapper;

}
