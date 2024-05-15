package com.codeshare.backend.controller.database;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("database")
public class TestController {

    @RequestMapping("/test/")
    public String test() {
        return "hello";
    }
}
