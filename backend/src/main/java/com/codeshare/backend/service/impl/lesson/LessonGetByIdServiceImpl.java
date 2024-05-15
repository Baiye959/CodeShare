package com.codeshare.backend.service.impl.lesson;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeshare.backend.mapper.LessonMapper;
import com.codeshare.backend.pojo.Lesson;
import com.codeshare.backend.service.lesson.LessonGetByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LessonGetByIdServiceImpl implements LessonGetByIdService {
    @Autowired
    private LessonMapper lessonMapper;
    @Override
    public List<Lesson> getbyid(Map<String, String> data) {
        int lessonId = Integer.parseInt(data.get("lesson_id"));

        QueryWrapper<Lesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", lessonId);
        return lessonMapper.selectList(queryWrapper);
    }
}
