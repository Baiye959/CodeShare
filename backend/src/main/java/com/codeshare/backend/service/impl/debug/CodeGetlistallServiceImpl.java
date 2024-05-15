package com.codeshare.backend.service.impl.debug;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeshare.backend.mapper.CodeMapper;
import com.codeshare.backend.pojo.Code;
import com.codeshare.backend.service.debug.CodeGetlistallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeGetlistallServiceImpl implements CodeGetlistallService {
      @Autowired
    private CodeMapper codeMapper;
      @Override
    public List<Code> getlistall(){
          QueryWrapper<Code> queryWrapper=new QueryWrapper<>();
          return codeMapper.selectList(queryWrapper);
      }
}
