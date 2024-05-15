package com.codeshare.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeshare.backend.pojo.Topic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopicMapper extends BaseMapper<Topic> {
}
