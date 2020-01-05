package org.nuc.course.controller;

import com.qiniu.util.Auth;
import org.nuc.course.core.Result;
import org.nuc.course.core.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @GetMapping("/token")
    public Result add() {
        String AK = "BAqdH3l9j51qCmQfxdU4OttJ_FlkFgzO7V_dEwsx";
        String SK = "rUYHYFqkOaTyuhuBI2oaZ5Td7ICrKJYGGrb71YOX";
        Auth auth = Auth.create(AK, SK);
        return ResultGenerator.genSuccessResult(auth.uploadToken("sql-20200105"));
    }
}
