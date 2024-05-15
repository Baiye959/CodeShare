package com.codeshare.backend.service.impl.database;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeshare.backend.mapper.TopicMapper;
import com.codeshare.backend.pojo.Topic;
import com.codeshare.backend.service.database.TopicGetByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TopicGetByIdServiceImpl implements TopicGetByIdService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<Topic> getbyid(Map<String, String> data) {
        int topicId = Integer.parseInt(data.get("topic_id"));

        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", topicId);
        return topicMapper.selectList(queryWrapper);
    }
}
