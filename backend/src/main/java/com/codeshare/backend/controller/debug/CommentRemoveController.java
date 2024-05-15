package com.codeshare.backend.controller.debug;

import com.codeshare.backend.service.debug.CommentRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommentRemoveController {
    @Autowired
    private CommentRemoveService commentRemoveService;

    @PostMapping("/api/debug/comment/remove/")
    public Map<String,String> remove(@RequestParam Map<String,String> data){
        return commentRemoveService.remove(data);
    }
}
