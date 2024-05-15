package com.codeshare.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123"));
        System.out.println(passwordEncoder.encode("124"));
        System.out.println(passwordEncoder.encode("125"));
        System.out.println(passwordEncoder.encode("126"));
//        System.out.println(passwordEncoder.matches("123", "$2a$10$vHv6nXX2JBQo0C.mDdTMXuXBuvhqEzk5KcJ8jkVmCAm7xqxJmX6uS"));
    }

}
