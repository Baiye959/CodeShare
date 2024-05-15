package com.codeshare.backend.service.impl.user.code;

import com.codeshare.backend.mapper.CodeMapper;
import com.codeshare.backend.pojo.Code;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.impl.util.UserDetailsImpl;
import com.codeshare.backend.service.user.code.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveServiceImpl implements RemoveService {
    @Autowired
    private CodeMapper codeMapper;

    @Override
    public Map<String, String> remove(Map<String, String> data) {

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int code_id = Integer.parseInt(data.get("code_id"));
        Code code = codeMapper.selectById(code_id);

        Map<String, String> map = new HashMap<>();
        if(code == null) {
            map.put("error_message", "代码不存在或已经被删除");
            return map;
        }
        if(!code.getUserId().equals(user.getId())) {
            map.put("error_message", "没有权限删除该代码");
            return map;
        }

        codeMapper.deleteById(code_id);
        map.put("error_message", "success");
        return map;
    }
}
