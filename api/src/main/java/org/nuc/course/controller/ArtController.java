package org.nuc.course.controller;
import org.nuc.course.core.Result;
import org.nuc.course.core.ResultGenerator;
import org.nuc.course.dto.ArtDTO;
import org.nuc.course.model.Art;
import org.nuc.course.db.service.ArtService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.nuc.course.utils.DateUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by xmmmmmovo on 2020/01/04.
*/
@RestController
@RequestMapping("/art")
public class ArtController {
    @Resource
    private ArtService artService;

    @PostMapping("/add")
    public Result add(Art art) {
        art.setCreateTime(DateUtils.getTimeStamp());
        art.setModifiedTime(DateUtils.getTimeStamp());
        artService.save(art);
        ArtDTO artDTO = artService.findADTO(art.getId());
        return ResultGenerator.genSuccessResult(artDTO);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id) {
        artService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PatchMapping("/update")
    public Result update(Art art) {
        artService.update(art);
        ArtDTO artDTO = artService.findADTO(art.getId());
        return ResultGenerator.genSuccessResult(artDTO);
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        ArtDTO artDTO = artService.findADTO(id);
        return ResultGenerator.genSuccessResult(artDTO);
    }

    @PostMapping("/list")
    public Result list(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ArtDTO> list = artService.findAllList();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/list_search")
    public Result listSearch(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size,
            @RequestParam String name
    ) {
        PageHelper.startPage(page, size);
        List<ArtDTO> list = artService.findUserLikeDTO(name);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/find_user_art_list")
    public Result listUserList(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size,
            @RequestParam Long id
    ) {
        PageHelper.startPage(page, size);
        List<ArtDTO> list = artService.findUserDTO(id);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/comment_up")
    public Result commentUp(@RequestParam Long id) {
        artService.updateCommentCount(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/like_up")
    public Result likeUp(@RequestParam Long id) {
        artService.updateLikeCount(id);
        return ResultGenerator.genSuccessResult();
    }
}
