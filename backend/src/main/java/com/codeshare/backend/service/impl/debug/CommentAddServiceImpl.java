package com.codeshare.backend.service.impl.debug;

import com.codeshare.backend.mapper.CommentMapper;
import com.codeshare.backend.pojo.Comment;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.debug.CommentAddService;
import com.codeshare.backend.service.impl.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommentAddServiceImpl implements CommentAddService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Integer codeId = Integer.parseInt(data.get("code_id"));
        String content=data.get("content");


        Map<String, String> map = new HashMap<>();
        if(content==null||content.length()==0){
            map.put("error_message","内容不能为空");
            return map;
        }
        if(content.length()>15000){
            map.put("error_message","内容不能超过15000");
            return map;
        }
        Date now = new Date();
        Comment comment=new Comment(null,content, user.getUsername(), now,codeId);
        commentMapper.insert(comment);
        map.put("error_message","success");

        return map;
    }
}
