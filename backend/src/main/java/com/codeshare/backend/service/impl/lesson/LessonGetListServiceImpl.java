package com.codeshare.backend.service.impl.lesson;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeshare.backend.mapper.LessonMapper;
import com.codeshare.backend.pojo.Lesson;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.impl.util.UserDetailsImpl;
import com.codeshare.backend.service.lesson.LessonGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonGetListServiceImpl implements LessonGetListService {
    @Autowired
    private LessonMapper lessonMapper;

    @Override
    public List<Lesson> getlist() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<Lesson> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        return lessonMapper.selectList(queryWrapper);
    }
}
