package cn.mango.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    //github头像获取
    private String avatarUrl;
}

