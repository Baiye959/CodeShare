package com.codeshare.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private Integer userId;
    private String lessonName;
}