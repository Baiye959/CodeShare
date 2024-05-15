package com.codeshare.backend.controller.database;

import com.codeshare.backend.pojo.Topic;
import com.codeshare.backend.service.database.TopicGetByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TopicGetByIdController {
    @Autowired
    private TopicGetByIdService topicGetByIdService;

    @GetMapping("/api/database/topic/getbyid/")
    public List<Topic> getbyid(@RequestParam Map<String, String> data) {
        return topicGetByIdService.getbyid(data);
    }
}
