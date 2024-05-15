package com.codeshare.backend.controller.debug;

import com.codeshare.backend.pojo.Code;
import com.codeshare.backend.service.debug.CodeGetlistallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CodeGetlistallController {
    @Autowired
    @Lazy
    private CodeGetlistallService codeGetlistallService;
    @GetMapping("/api/debug/code/getlistall/")
    public List<Code> getlistall(){
        return codeGetlistallService.getlistall();
    }
}
