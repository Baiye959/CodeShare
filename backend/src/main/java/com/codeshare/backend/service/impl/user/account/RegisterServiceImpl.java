package com.codeshare.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeshare.backend.mapper.UserMapper;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String usermname, String password, String confirmedPassword) {
        Map<String, String> map = new HashMap<>();
        if (usermname == null) {
            map.put("error_message", "用户名不能为空");
            return map;
        }
        if (password == null || confirmedPassword == null) {
            map.put("error_message", "密码不能为空");
            return map;
        }
        usermname = usermname.trim();
        if (usermname.length() == 0) {
            map.put("error_message", "用户名不能为空");
            return map;
        }
        if(password.length() == 0 || confirmedPassword.length() == 0) {
            map.put("error_message", "密码不能为空");
            return map;
        }
        if(usermname.length() >= 100) {
            map.put("error_message", "用户名长度不能大于100");
            return map;
        }
        if(password.length() >= 100 || confirmedPassword.length() >= 100) {
            map.put("error_message", "密码长度不能大于100");
            return map;
        }
        if( !password.equals(confirmedPassword) ) {
            map.put("error_message", "两次输入的密码不一致");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", usermname);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            map.put("error_message", "用户名已存在");
            return map;
        }

        String encodePassword = passwordEncoder.encode(password);
        String photo = "https://cdn.acwing.com/media/user/profile/photo/136456_lg_81782d4cfd.png";
        User user = new User(null, usermname, encodePassword, photo, null);
        userMapper.insert(user);

        map.put("error_message", "success");
        return map;
    }
}
