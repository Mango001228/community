package cn.mango.community.controller;

import cn.mango.community.mapper.QuestionMapper;
import cn.mango.community.model.Question;
import cn.mango.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class PublishController {

    //自动装配
    @Autowired
    private QuestionMapper questionMapper;

    /*
    点击页面，获取到一个id，用id去question中获取到当前的问题，响应回页面
    */
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id){
        Question question = questionMapper.getById(id);

        return "publish";
    }

    @GetMapping("/publish")
    public String publish(){
       return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        //不能为空，否则传递提示信息
        if (title == null || title.equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description == null || description.equals("")){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        //用户不存在，向页面前端发送提示信息
        if (user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        //前面工作都就位后，先数据库加入数据
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
