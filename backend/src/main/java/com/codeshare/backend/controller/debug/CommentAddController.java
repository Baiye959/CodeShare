package com.codeshare.backend.controller.debug;

import com.codeshare.backend.service.debug.CommentAddService;
import com.codeshare.backend.service.user.code.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommentAddController {

    @Autowired
    private CommentAddService commentAddService;

    @PostMapping("/api/debug/comment/add/")
    public Map<String,String> add(@RequestParam Map<String,String> data){
        return commentAddService.add(data);
    }
}
