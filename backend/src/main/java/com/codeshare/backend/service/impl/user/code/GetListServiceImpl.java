package com.codeshare.backend.service.impl.user.code;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeshare.backend.mapper.CodeMapper;
import com.codeshare.backend.pojo.Code;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.impl.util.UserDetailsImpl;
import com.codeshare.backend.service.user.code.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetListServiceImpl implements GetListService {
    @Autowired
    private CodeMapper codeMapper;

    @Override
    public List<Code> getlist() {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        QueryWrapper<Code> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        return codeMapper.selectList(queryWrapper);
    }
}
