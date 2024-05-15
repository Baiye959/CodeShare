package com.codeshare.backend.controller.user.account;

import com.codeshare.backend.service.user.account.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserUpdateController {
    @Autowired
    private UserUpdateService userUpdateService;

    @PostMapping("/api/user/account/update/")
    public Map<String, String> update(@RequestParam Map<String, String> data) {
        return userUpdateService.update(data);
    }
}
