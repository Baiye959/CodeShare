package com.codeshare.backend.controller.lesson;

import com.codeshare.backend.pojo.Lesson;
import com.codeshare.backend.service.lesson.LessonGetListByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LessonGetListByNameController {
    @Autowired
    private LessonGetListByNameService lessonGetListByNameService;

    @GetMapping("/api/lesson/getlistbyname/")
    public List<Lesson> getlistbyname(@RequestParam Map<String, String> data) {
        return lessonGetListByNameService.getlistbyname(data);
    }
}
