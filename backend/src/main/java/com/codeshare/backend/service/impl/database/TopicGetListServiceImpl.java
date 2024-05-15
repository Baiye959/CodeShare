package com.codeshare.backend.service.impl.database;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeshare.backend.mapper.TopicMapper;
import com.codeshare.backend.pojo.Topic;
import com.codeshare.backend.service.database.TopicGetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TopicGetListServiceImpl implements TopicGetListService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public List<Topic> getlist(Map<String, String> data) {
        String test_name = data.get("test_name");

        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("test_name", test_name);
        return topicMapper.selectList(queryWrapper);
    }
}
