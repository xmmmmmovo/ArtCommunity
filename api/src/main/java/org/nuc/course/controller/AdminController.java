package org.nuc.course.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.nuc.course.core.Result;
import org.nuc.course.core.ResultGenerator;
import org.nuc.course.core.ServiceException;
import org.nuc.course.db.service.AdminService;
import org.nuc.course.model.Admin;
import org.nuc.course.model.User;
import org.nuc.course.utils.BCryptUtils;
import org.nuc.course.utils.DTOUtils;
import org.nuc.course.utils.IdUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Date;
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
        admin.setToken(IdUtils.uuid64());
        admin.setAdminPassword(BCryptUtils.encode(admin.getAdminPassword()));
        admin.setRegisterTime(new Date());
        adminService.save(admin);
        return ResultGenerator.genSuccessResult(DTOUtils.adminToDTO(admin));
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        adminService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PatchMapping("/update")
    public Result update(Admin admin) {
        adminService.update(admin);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Admin admin = adminService.findById(id);
        return ResultGenerator.genSuccessResult(admin);
    }

    @PostMapping("/token_detail")
    public Result detailByToken(@RequestParam String token) {
        Admin admin = adminService.findBy("token", token);
        return ResultGenerator.genSuccessResult(DTOUtils.adminToDTO(admin));
    }

    @PostMapping("/login")
    public Result adminLogin(
            @RequestParam String adminName,
            @RequestParam String adminPassword
    ) {
        Admin admin = adminService.findBy("adminName", adminName);
        if (BCryptUtils.match(adminPassword, admin.getAdminPassword())) {
            return ResultGenerator.genSuccessResult(DTOUtils.adminToDTO(admin));
        } else {
            return ResultGenerator.genFailResult("账号密码错误, 请检查");
        }
    }

    /**
     * 分页查询
     * */
    @GetMapping("/list")
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
