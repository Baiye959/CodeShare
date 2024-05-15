package com.codeshare.backend.service.impl.user.code;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeshare.backend.mapper.TopicMapper;
import com.codeshare.backend.pojo.Topic;
import com.codeshare.backend.service.user.code.TopicGetListAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicGetListAllServiceImpl implements TopicGetListAllService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<Topic> getlistall() {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        return topicMapper.selectList(queryWrapper);
    }
}
