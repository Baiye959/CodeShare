package com.codeshare.backend.service.impl.debug;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeshare.backend.mapper.CommentMapper;
import com.codeshare.backend.pojo.Comment;
import com.codeshare.backend.service.debug.CommentGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentGetListServiceImpl implements CommentGetListService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> getlist(){
        QueryWrapper<Comment> queryWrapper=new QueryWrapper<>();
        return commentMapper.selectList(queryWrapper);
    }
}
