package com.codeshare.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeshare.backend.pojo.Test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper extends BaseMapper<Test> {
}
