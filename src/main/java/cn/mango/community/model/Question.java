package cn.mango.community.model;

import lombok.Data;

/*
    @Date：自动生成getter，setter，toString，EqualsAndHashCode
*/
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
}