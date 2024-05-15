package com.codeshare.backend.controller.lesson;

import com.codeshare.backend.service.lesson.LessonAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LessonAddController {
    @Autowired
    private LessonAddService lessonAddService;

    @PostMapping("/api/lesson/add/")
    public Map<String, String> add(@RequestParam Map<String, String> data) {
        return lessonAddService.add(data);
    }
}
