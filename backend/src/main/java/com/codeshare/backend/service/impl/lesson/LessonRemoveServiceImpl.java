package com.codeshare.backend.service.impl.lesson;

import com.codeshare.backend.mapper.LessonMapper;
import com.codeshare.backend.pojo.Lesson;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.impl.util.UserDetailsImpl;
import com.codeshare.backend.service.lesson.LessonRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LessonRemoveServiceImpl implements LessonRemoveService {
    @Autowired
    private LessonMapper lessonMapper;

    @Override
    public Map<String, String> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int lesson_id = Integer.parseInt(data.get("lesson_id"));
        Lesson lesson = lessonMapper.selectById(lesson_id);

        Map<String, String> map = new HashMap<>();
        if(lesson == null) {
            map.put("error_message", "推荐不存在或已经被删除");
            return map;
        }
        if(!lesson.getUserId().equals(user.getId())) {
            map.put("error_message", "没有权限删除该代码");
            return map;
        }

        lessonMapper.deleteById(lesson_id);
        map.put("error_message", "success");
        return map;
    }
}
