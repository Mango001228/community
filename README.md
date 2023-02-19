## 芒果代码社区  

## 资料  
[Spring 文档](http://spring.io/guides)  
[Spring web](http://spring.io/guides/gs/serving-web-content/)  
[es](https://elasticsearch.cn/explore)  
[GitHub deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)  
[bootstrap](https://v3.bootcss.com/getting-started/)  
[GitHub OAuth](https://docs.github.com/en/apps/oauth-apps/building-oauth-apps/creating-an-oauth-app)  
[Spring Mybatis](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)  

## 工具  
[Git](http://git-scm.com/download)  
[Visual Paradigm](http://www.visual-paradigm.com)

## 脚本
```sql
create table user
(
    id int auto_increment primary key,
    name         varchar(100) ,
    account_id   varchar(50) ,
    token        char(36) ,
    gmt_create   bigint null,
    gmt_modified bigint null
);
```