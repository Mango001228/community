## 芒果代码社区  

## 资料  
[Spring 文档](http://spring.io/guides)  
[Spring web](http://spring.io/guides/gs/serving-web-content/)  
[es](https://elasticsearch.cn/explore)  
[GitHub deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)  
[bootstrap](https://v3.bootcss.com/getting-started/)  
[GitHub OAuth](https://docs.github.com/en/apps/oauth-apps/building-oauth-apps/creating-an-oauth-app)  
[Spring Mybatis](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)  
[Thymeleaf](https://www.thymeleaf.org/documentation.html)  
[Spring MVC](https://docs.spring.io/spring/docs)  

## 工具  
[Git](http://git-scm.com/download)  
[Visual Paradigm](http://www.visual-paradigm.com)
[Lombok](http://www.projectlombok.org)

## 脚本
```sql
create table user
(
    id           int auto_increment
        primary key,
    name         varchar(100) null,
    account_id   varchar(50)  null,
    token        char(36)     null,
    gmt_create   bigint       null,
    gmt_modified bigint       null,
    bio          varchar(256) null,
    avatar_url   varchar(100) null
);

create table question
(
    id            int auto_increment
        primary key,
    title         varchar(50)   null,
    description   text          null,
    gmt_create    bigint        null,
    gmt_modified  bigint        null,
    creator       int           null,
    comment_count int default 0 null comment '评论数',
    view_count    int default 0 null comment '阅读数',
    like_count    int default 0 null comment '点赞数',
    tag           varchar(256)  null comment '标签'
);
```
```bash
mvn -Dmybatsi.generator.overwrite-true mybatis-generator:generator
```