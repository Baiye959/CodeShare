package com.codeshare.backend.service.impl.lesson;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeshare.backend.mapper.LessonMapper;
import com.codeshare.backend.pojo.Lesson;
import com.codeshare.backend.service.lesson.LessonGetListByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LessonGetListByNameImpl implements LessonGetListByNameService {
    @Autowired
    private LessonMapper lessonMapper;

    @Override
    public List<Lesson> getlistbyname(Map<String, String> data) {
        String lesson_name = data.get("lesson_name");

        QueryWrapper<Lesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lesson_name", lesson_name);
        return lessonMapper.selectList(queryWrapper);
    }
}
