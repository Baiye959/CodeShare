package com.codeshare.backend.service.database;

import com.codeshare.backend.pojo.Topic;

import java.util.List;
import java.util.Map;

public interface TopicGetByIdService {
    List<Topic> getbyid(Map<String, String> data);
}
