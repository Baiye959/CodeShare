package com.codeshare.backend.service.lesson;

import com.codeshare.backend.pojo.Lesson;

import java.util.List;
import java.util.Map;

public interface LessonGetByIdService {
    List<Lesson> getbyid(Map<String, String> data);
}
