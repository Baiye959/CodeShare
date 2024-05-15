package com.codeshare.backend.service.impl.user.code;

import com.codeshare.backend.mapper.CodeMapper;
import com.codeshare.backend.pojo.Code;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.impl.util.UserDetailsImpl;
import com.codeshare.backend.service.user.code.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    private CodeMapper codeMapper;

    @Override
    public Map<String, String> update(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int code_id = Integer.parseInt(data.get("code_id"));
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

        Code code = codeMapper.selectById(code_id);
        if(code == null) {
            map.put("error_message", "代码不存在或已被删除");
            return map;
        }
        if(!code.getUserId().equals(user.getId())) {
            map.put("error_message", "没有权限修改该代码");
            return map;
        }

        Code new_code = new Code(
                code.getId(),
                user.getId(),
                title,
                description,
                content,
                isPassed,
                code.getCreatetime(),
                new Date(),
                topicId
        );

        codeMapper.updateById(new_code);
        map.put("error_message", "success");
        return map;
    }
}
