package com.codeshare.backend.service.impl.user.code;

import com.codeshare.backend.mapper.CodeMapper;
import com.codeshare.backend.pojo.Code;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.impl.util.UserDetailsImpl;
import com.codeshare.backend.service.user.code.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {
    @Autowired
    private CodeMapper codeMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");
        String isPassed = data.get("is_passed");
        String topicId = data.get("topic_id");

        Map<String, String> map = new HashMap<>();

        if(title == null || title.length() == 0){
            map.put("error_message", "标题不能为空");
            return map;
        }
        if(title.length() > 100){
            map.put("error_message", "标题长度不能大于100");
            return map;
        }
        if(description != null && description.length() > 300){
            map.put("error_message", "描述长度不能大于300");
            return map;
        }
        if(content == null || content.length() == 0){
            map.put("error_message", "代码不能为空");
            return map;
        }
        if(content.length() > 10000){
            map.put("error_message", "代码长度不能大于10000");
            return map;
        }

        Date now = new Date();
        Code code = new Code(null, user.getId(), title, description, content, isPassed, now, now, topicId);

        codeMapper.insert(code);
        map.put("error_message", "success");

        return map;
    }
}
