package com.codeshare.backend.service.database;

import com.codeshare.backend.pojo.Topic;

import java.util.List;
import java.util.Map;

public interface TopicGetListService {
    List<Topic> getlist(Map<String, String> data);
}
