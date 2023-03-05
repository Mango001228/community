package cn.mango.community.advice;

import cn.mango.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
/*
    https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-error-handling-custom-error-pages
    spring官方文档——异常处理
*/
@ControllerAdvice
public class CustomizeExceptionHandler {
    //全局处理
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model){
        //左侧是否为右侧的实例
        if (e instanceof CustomizeException) {
            //知道是什么原因导致的报错
            model.addAttribute("message",e.getMessage());
        } else {
            //确实处理不了,未知报错，需要查询日志
            model.addAttribute("message","服务冒烟了，请稍后重试！");
        }
        return new ModelAndView("error");
    }

}
