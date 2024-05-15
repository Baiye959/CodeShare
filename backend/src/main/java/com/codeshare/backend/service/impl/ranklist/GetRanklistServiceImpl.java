package com.codeshare.backend.service.impl.ranklist;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codeshare.backend.mapper.UserMapper;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.ranklist.GetRanklistService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRanklistServiceImpl implements GetRanklistService {
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public JSONObject getlist(Integer page) {
        IPage<User> userIPage=new Page<>(page,10);
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.orderByDesc("rating");
        List<User> users=userMapper.selectPage(userIPage,queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        for (User user:users)
            user.setPassword("");
        resp.put("users",users);
        resp.put("users_count",userMapper.selectCount(null));
        
        return resp;
    }
}
