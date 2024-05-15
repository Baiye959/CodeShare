package com.codeshare.backend.controller.lesson;

import com.codeshare.backend.pojo.Lesson;
import com.codeshare.backend.service.lesson.LessonGetListAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LessonGetListAllController {
    @Autowired
    private LessonGetListAllService lessonGetListAllService;
    @GetMapping("/api/lesson/getlistall/")
    public List<Lesson> getliatall() {
        return lessonGetListAllService.getlistall();
    }
}
