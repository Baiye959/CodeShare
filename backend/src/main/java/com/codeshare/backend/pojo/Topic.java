package com.codeshare.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String testName;
    private String topicName;
    private String topicContent;
}