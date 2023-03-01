package cn.mango.community.controller;

import cn.mango.community.dto.PaginationDTO;
import cn.mango.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size){
        //获取分页数据，存入pagination
        PaginationDTO pagination = questionService.list(page,size);
        //映射到页面上
        model.addAttribute("pagination",pagination);
        return "index";
    }
}






