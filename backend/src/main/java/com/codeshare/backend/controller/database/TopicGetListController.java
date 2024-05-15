package com.codeshare.backend.controller.database;

import com.codeshare.backend.pojo.Topic;
import com.codeshare.backend.service.database.TopicGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TopicGetListController {
    @Autowired
    private TopicGetListService topicGetListService;

    @GetMapping("/api/database/topic/getlist/")
    public List<Topic> getlist(@RequestParam Map<String, String> data) {
        return topicGetListService.getlist(data);
    }
}