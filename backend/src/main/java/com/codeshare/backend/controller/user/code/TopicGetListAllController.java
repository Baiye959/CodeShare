package com.codeshare.backend.controller.user.code;

import com.codeshare.backend.pojo.Topic;
import com.codeshare.backend.service.user.code.TopicGetListAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicGetListAllController {
    @Autowired
    private TopicGetListAllService topicGetListAllService;

    @GetMapping("/api/topic/getlistall/")
    public List<Topic> getlistall() {
        return topicGetListAllService.getlistall();
    }
}