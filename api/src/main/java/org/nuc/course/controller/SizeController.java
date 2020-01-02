package org.nuc.course.controller;

import org.nuc.course.core.Result;
import org.nuc.course.core.ResultGenerator;
import org.nuc.course.db.service.SizeService;
import org.nuc.course.model.Size;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by xmmmmmovo on 2020/01/02.
*/
@RestController
@RequestMapping("/size")
public class SizeController {
    @Resource
    private SizeService sizeService;

    @PostMapping("/add")
    public Result add(Size size) {
        sizeService.save(size);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        sizeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Size size) {
        sizeService.update(size);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Size size = sizeService.findById(id);
        return ResultGenerator.genSuccessResult(size);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Size> list = sizeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
