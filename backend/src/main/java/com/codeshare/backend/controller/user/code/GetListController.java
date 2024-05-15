package com.codeshare.backend.controller.user.code;

import com.codeshare.backend.pojo.Code;
import com.codeshare.backend.service.user.code.GetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetListController {
    @Autowired
    private GetListService getListService;

    @GetMapping("/api/user/code/getlist/")
    public List<Code> getList() {
        return getListService.getlist();
    }
}
