package com.codeshare.backend.service.impl.lesson;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeshare.backend.mapper.LessonMapper;
import com.codeshare.backend.pojo.Lesson;
import com.codeshare.backend.service.lesson.LessonGetListAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonGetListAllServiceImpl implements LessonGetListAllService {
    @Autowired
    private LessonMapper lessonMapper;
    @Override
    public List<Lesson> getlistall() {
        QueryWrapper<Lesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("lesson_name");
        return lessonMapper.selectList(queryWrapper);
    }
}
