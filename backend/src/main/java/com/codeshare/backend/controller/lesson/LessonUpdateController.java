package com.codeshare.backend.controller.lesson;

import com.codeshare.backend.service.lesson.LessonUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LessonUpdateController {
    @Autowired
    private LessonUpdateService lessonUpdateService;

    @PostMapping("/api/lesson/update/")
    public Map<String, String> update(@RequestParam Map<String, String> data) {
        return lessonUpdateService.update(data);
    }
}
