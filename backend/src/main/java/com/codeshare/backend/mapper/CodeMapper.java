package com.codeshare.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeshare.backend.pojo.Code;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeMapper extends BaseMapper<Code> {
}
