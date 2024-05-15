package com.codeshare.backend.service.impl.user.account;

import com.codeshare.backend.mapper.UserMapper;
import com.codeshare.backend.pojo.User;
import com.codeshare.backend.service.impl.util.UserDetailsImpl;
import com.codeshare.backend.service.user.account.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserUpdateServiceImpl implements UserUpdateService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, String> update(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int rating_change = Integer.parseInt(data.get("rating_change"));

        Map<String, String> map = new HashMap<>();

        int new_rating = user.getRating() + rating_change;

        User new_user = new User(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getPhoto(),
                new_rating
        );

        userMapper.updateById(new_user);
        map.put("error_message", "success");
        return map;
    }
}
