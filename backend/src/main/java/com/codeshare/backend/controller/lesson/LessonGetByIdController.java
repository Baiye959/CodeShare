package com.codeshare.backend.controller.lesson;

import com.codeshare.backend.pojo.Lesson;
import com.codeshare.backend.service.lesson.LessonGetByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class LessonGetByIdController {
    @Autowired
    private LessonGetByIdService lessonGetByIdService;
    @GetMapping("/api/lesson/getbyid/")
    public List<Lesson> getbyid(@RequestParam Map<String, String> data){
        return lessonGetByIdService.getbyid(data);
    }
}
