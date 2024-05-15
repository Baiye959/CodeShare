package com.codeshare.backend.service.database;

import com.codeshare.backend.pojo.Code;

import java.util.List;
import java.util.Map;

public interface TopicMyCodeService {
    List<Code> mycode(Map<String, String> data);
}
