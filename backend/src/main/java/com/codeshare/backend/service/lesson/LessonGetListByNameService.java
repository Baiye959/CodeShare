package com.codeshare.backend.service.lesson;

import com.codeshare.backend.pojo.Lesson;

import java.util.List;
import java.util.Map;

public interface LessonGetListByNameService {
    List<Lesson> getlistbyname(Map<String, String> data);
}
