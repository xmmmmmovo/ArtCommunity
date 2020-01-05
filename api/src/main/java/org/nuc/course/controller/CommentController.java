package org.nuc.course.controller;
import org.nuc.course.core.Result;
import org.nuc.course.core.ResultGenerator;
import org.nuc.course.model.Comment;
import org.nuc.course.db.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by xmmmmmovo on 2020/01/04.
*/
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @PostMapping("/add")
    public Result add(Comment comment) {
        commentService.save(comment);
        comment = commentService.findById(comment.getId());
        return ResultGenerator.genSuccessResult(comment);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        commentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Comment comment) {
        commentService.update(comment);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        Comment comment = commentService.findById(id);
        return ResultGenerator.genSuccessResult(comment);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Comment> list = commentService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
