package com.codeshare.backend.service.impl.debug;

import com.codeshare.backend.mapper.CodeMapper;
import com.codeshare.backend.mapper.CommentMapper;
import com.codeshare.backend.pojo.Code;
import com.codeshare.backend.pojo.Comment;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.debug.CommentRemoveService;
import com.codeshare.backend.service.impl.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CommentRemoveServiceImpl implements CommentRemoveService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Map<String,String> remove(Map<String,String> data){
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int comment_id = Integer.parseInt(data.get("comment_id"));
        Comment comment=commentMapper.selectById(comment_id);

        Map<String,String> map=new HashMap<>();
        if(comment==null){
            map.put("error_message","评论不存在或者已经被删除");
            return map;
        }
        if(!comment.getUserName().equals(user.getUsername())){
            map.put("error_message","没有权限删除该评论");
            return map;
        }
        commentMapper.deleteById(comment_id);
        map.put("error_message","success");
        return map;
    }
}
