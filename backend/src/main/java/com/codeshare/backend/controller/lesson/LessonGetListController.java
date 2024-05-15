package com.codeshare.backend.controller.lesson;

import com.codeshare.backend.pojo.Lesson;
import com.codeshare.backend.service.lesson.LessonGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LessonGetListController {
    @Autowired
    private LessonGetListService lessonGetListService;

    @GetMapping("/api/lesson/getlist/")
    public List<Lesson> getlist() {
        return lessonGetListService.getlist();
    }
}
