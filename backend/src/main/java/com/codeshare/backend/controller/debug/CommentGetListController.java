package com.codeshare.backend.controller.debug;

import com.codeshare.backend.pojo.Comment;
import com.codeshare.backend.service.debug.CommentGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentGetListController {
    @Autowired
    private CommentGetListService commentGetListService;

    @GetMapping("/api/debug/comment/getlist/")
    public List<Comment> getlist(){
        return commentGetListService.getlist();
    }
}
