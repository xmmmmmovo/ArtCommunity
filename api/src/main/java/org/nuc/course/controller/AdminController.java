package org.nuc.course.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.nuc.course.core.Result;
import org.nuc.course.core.ResultGenerator;
import org.nuc.course.core.ServiceException;
import org.nuc.course.db.service.AdminService;
import org.nuc.course.model.Admin;
import org.nuc.course.utils.BCryptUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xmmmmmovo on 2019/12/31.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    @PostMapping("/add")
    public Result add(Admin admin) {
        admin.setAdminPassword(BCryptUtils.encode(admin.getAdminPassword()));
        adminService.save(admin);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        adminService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Admin admin) {
        adminService.update(admin);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(
            @RequestParam String adminEmail,
            @RequestParam String adminPassword
    ) {
        Admin admin = adminService.findBy("admin_email", adminEmail);
        if (BCryptUtils.match(adminPassword, admin.getAdminPassword())) {
            return ResultGenerator.genSuccessResult(admin);
        } else {
            return ResultGenerator.genFailResult("账号密码错误, 请检查");
        }
    }

    /**
     * 分页查询
     * */
    @PostMapping("/list")
    public Result list(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size
    ) {
        PageHelper.startPage(page, size);
        List<Admin> list = adminService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
