package com.codeshare.backend.service.impl.lesson;

import com.codeshare.backend.mapper.LessonMapper;
import com.codeshare.backend.pojo.Lesson;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.impl.util.UserDetailsImpl;
import com.codeshare.backend.service.lesson.LessonAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LessonAddServiceImpl implements LessonAddService {

    @Autowired
    private LessonMapper lessonMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        String title = data.get("title");
        String description = data.get("description");
        String lessonName = data.get("lesson_name");

        Map<String, String> map = new HashMap<>();

        if(title == null || title.length() == 0) {
            map.put("error_message", "标题不能为空");
            return map;
        }
        if(title.length() > 100) {
            map.put("error_message", "标题长度不能大于100");
            return map;
        }
        if(description == null || description.length() == 0) {
            description = "这个用户很懒，什么也没留下";
        }

        if(description.length() > 300) {
            map.put("error_message", "描述长度不能大于300");
            return map;
        }
        if(lessonName == null || lessonName.length() == 0) {
            map.put("error_message", "课程名不能为空");
            return map;
        }
        if(lessonName.length() > 100) {
            map.put("error_message", "课程名不能大于100");
            return map;
        }

        Lesson lesson = new Lesson(null, title, description, user.getId(), lessonName);
        lessonMapper.insert(lesson);
        map.put("error_message", "success");

        return map;
    }
}
