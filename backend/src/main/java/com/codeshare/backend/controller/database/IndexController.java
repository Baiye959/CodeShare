package com.codeshare.backend.controller.database;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/database/")
public class IndexController {

    @RequestMapping("index/")
    public String index() {
        return "database/index";
    }
}
