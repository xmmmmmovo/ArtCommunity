package org.nuc.course.controller;
import org.nuc.course.core.Result;
import org.nuc.course.core.ResultGenerator;
import org.nuc.course.model.Admin;
import org.nuc.course.model.Size;
import org.nuc.course.db.service.SizeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.nuc.course.model.User;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by xmmmmmovo on 2020/01/04.
*/
@RestController
@RequestMapping("/size")
public class SizeController {
    @Resource
    private SizeService sizeService;

    @PostMapping("/add")
    public Result add(Size size) {
        sizeService.save(size);
        return ResultGenerator.genSuccessResult(size);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id) {
        sizeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PatchMapping("/update")
    public Result update(Size size) {
        sizeService.update(size);
        return ResultGenerator.genSuccessResult(size);
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Long id) {
        Size size = sizeService.findById(id);
        return ResultGenerator.genSuccessResult(size);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Size> list = sizeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/all")
    public Result all() {
        List<Size> list = sizeService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }
}
