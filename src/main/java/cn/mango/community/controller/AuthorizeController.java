package cn.mango.community.controller;

import cn.mango.community.dto.AccessTokenDTO;
import cn.mango.community.dto.GithubUser;
import cn.mango.community.mapper.UserMapper;
import cn.mango.community.model.User;
import cn.mango.community.provider.GithubProvider;
import cn.mango.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        //从GitHub获得用户登录后
        if(githubUser!=null && githubUser.getId() != null){
            User user = new User();
            //获取user中的token\name\accountId\创建时间与修改时间
            //抽象出token代替session，以token为验证前后端登录的验证
            /*需要返工，不懂该实现*/
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            //获取头像
            user.setAvatarUrl(githubUser.getAvatarUrl());
            //写入数据库中
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/"; //redirect返回的是路径，不是地址
        }else {
            //登陆失败，重新登录
            return "redirect:/"; //redirect返回的是路径，不是地址
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        //通过request获取session并将其移除，通过response获取token（cookie）并将其移除
        //cookie相当于银行，session相当于银行账户）
        request.getSession().removeAttribute("user");
        /*
        删除已知名称的Cookie（方案：重新建立同名立即删除类型的Cookie）
        Cookie cookie = new Cookie("username",null); 假设要删除名称未username的Cookie
        cookie.setMaxAge(0); 立即删除型
        response.addCookie(cookie); 重新写入覆盖之前的
        */
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
