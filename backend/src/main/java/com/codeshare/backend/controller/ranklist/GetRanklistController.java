package com.codeshare.backend.controller.ranklist;

import com.alibaba.fastjson.JSONObject;
import com.codeshare.backend.service.ranklist.GetRanklistService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetRanklistController {
    @Autowired
    private GetRanklistService getRanklistService;
    @GetMapping("/api/ranklist/getlist/")
    public JSONObject getlist(@RequestParam Map<String,String> data){
        Integer page =Integer.parseInt(data.get("page"));
        return getRanklistService.getlist(page);
    }
}
