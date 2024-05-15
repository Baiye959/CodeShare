package com.codeshare.backend.controller.lesson;

import com.codeshare.backend.service.lesson.LessonRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LessonRemoveController {
    @Autowired
    private LessonRemoveService lessonRemoveService;

    @PostMapping("/api/lesson/remove/")
    public Map<String, String> remove(@RequestParam Map<String, String> data) {
        return lessonRemoveService.remove(data);
    }
}
