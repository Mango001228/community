package cn.mango.community.interceptor;

import cn.mango.community.mapper.UserMapper;
import cn.mango.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        //循环遍历cookies，查找cookies中的key值
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                //key值是否有等于token
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    //通过findByToken方法，获取user对象
                    User user = userMapper.findByToken(token);
                    //获取的user对象部不为空，登录成功
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
