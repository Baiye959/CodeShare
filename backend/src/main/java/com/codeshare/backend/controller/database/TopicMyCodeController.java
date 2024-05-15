package com.codeshare.backend.controller.database;

import com.codeshare.backend.pojo.Code;
import com.codeshare.backend.service.database.TopicMyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TopicMyCodeController {
    @Autowired
    private TopicMyCodeService topicMyCodeService;

    @GetMapping("/api/database/topic/mycode/")
    public List<Code> mycode(@RequestParam Map<String, String> data) {
        return topicMyCodeService.mycode(data);
    }
}
