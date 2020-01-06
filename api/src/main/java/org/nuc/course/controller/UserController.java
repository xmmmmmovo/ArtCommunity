package org.nuc.course.controller;

import org.nuc.course.core.Result;
import org.nuc.course.core.ResultGenerator;
import org.nuc.course.db.service.UserService;
import org.nuc.course.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.nuc.course.utils.BCryptUtils;
import org.nuc.course.utils.DateUtils;
import org.nuc.course.utils.IdUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xmmmmmovo on 2020/01/02.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result add(User user) {
        user.setToken(IdUtils.uuid64());
        user.setRegisterTime(DateUtils.getTimeStamp());
        user.setUserPassword(BCryptUtils.encode(user.getUserPassword()));
        userService.save(user);
        user = userService.findById(user.getId());
        return ResultGenerator.genSuccessResult(user);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PatchMapping("/update")
    public Result update(User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult(user);
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Long id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/token_detail")
    public Result detailByToken(@RequestParam String token) {
        User user = userService.findBy("token", token);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/all")
    public Result all() {
        List<User> list = userService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }
}
