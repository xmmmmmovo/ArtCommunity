package org.nuc.course.controller;
import org.nuc.course.core.Result;
import org.nuc.course.core.ResultGenerator;
import org.nuc.course.model.Tag;
import org.nuc.course.db.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.nuc.course.model.User;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by xmmmmmovo on 2020/01/04.
*/
@RestController
@RequestMapping("/tag")
public class TagController {
    @Resource
    private TagService tagService;

    @PostMapping("/add")
    public Result add(Tag tag) {
        if (tag.getUsageCount() == null) {
            tag.setUsageCount((long) 0);
        }
        tagService.save(tag);
        return ResultGenerator.genSuccessResult(tag);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id) {
        tagService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PatchMapping("/update")
    public Result update(Tag tag) {
        tagService.update(tag);
        return ResultGenerator.genSuccessResult(tag);
    }

    @GetMapping("/detail")
    public Result detail(@RequestParam Long id) {
        Tag tag = tagService.findById(id);
        return ResultGenerator.genSuccessResult(tag);
    }

    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Tag> list = tagService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/all")
    public Result all() {
        List<Tag> list = tagService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }
}
