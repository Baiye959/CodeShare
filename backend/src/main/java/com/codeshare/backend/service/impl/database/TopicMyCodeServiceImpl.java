package com.codeshare.backend.service.impl.database;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeshare.backend.mapper.CodeMapper;
import com.codeshare.backend.pojo.Code;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.database.TopicMyCodeService;
import com.codeshare.backend.service.impl.util.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TopicMyCodeServiceImpl implements TopicMyCodeService {
    @Autowired
    private CodeMapper codeMapper;

    @Override
    public List<Code> mycode(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

//        int topic_id = Integer.parseInt(data.get("topic_id"));
        String topic_name = data.get("topic_name");

        QueryWrapper<Code> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        queryWrapper.eq("topic_id", topic_name);

        return codeMapper.selectList(queryWrapper);
    }
}
