package org.nuc.course.controller;

import com.alibaba.druid.sql.visitor.functions.Isnull;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.nuc.course.core.Result;
import org.nuc.course.core.ResultGenerator;
import org.nuc.course.db.service.AdminService;
import org.nuc.course.model.Admin;
import org.nuc.course.utils.BCryptUtils;
import org.nuc.course.utils.DateUtils;
import org.nuc.course.utils.IdUtils;
import org.springframework.web.bind.annotation.*;

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
        admin.setToken(IdUtils.uuid64());
        admin.setAdminPassword(BCryptUtils.encode(admin.getAdminPassword()));
        admin.setRegisterTime(DateUtils.getTimeStamp());
        adminService.save(admin);
        admin = adminService.findById(admin.getId());
        return ResultGenerator.genSuccessResult(admin);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id) {
        adminService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PatchMapping("/update")
    public Result update(Admin admin) {
        adminService.update(admin);
        admin = adminService.findById(admin.getId());
        return ResultGenerator.genSuccessResult(admin);
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam String id) {
        Admin admin = adminService.findById(Long.parseLong(id));
        return ResultGenerator.genSuccessResult(admin);
    }

    @GetMapping("/detail_all_token")
    public Result detailTokenAll(@RequestParam String token) {
        Admin admin = adminService.findBy("token", token);
        return ResultGenerator.genSuccessResult(admin);
    }

    @PostMapping("/token_detail")
    public Result detailByToken(@RequestParam String token) {
        Admin admin = adminService.findBy("token", token);
        return ResultGenerator.genSuccessResult(admin);
    }

    @PostMapping("/login")
    public Result adminLogin(
            @RequestParam String adminName,
            @RequestParam String adminPassword
    ) {
        Admin admin = adminService.findBy("adminName", adminName);
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
            @RequestParam(defaultValue = "0") Integer size,
            @RequestParam(defaultValue = "") String name
    ) {
        PageHelper.startPage(page, size);
        List<Admin> list;
        if (name.equals("")) {
            list = adminService.findAll();
        } else {
            list = adminService.findByAndReturnList("adminName", name);
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
