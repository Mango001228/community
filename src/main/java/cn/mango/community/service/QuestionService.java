package cn.mango.community.service;

import cn.mango.community.dto.PaginationDTO;
import cn.mango.community.dto.QuestionDTO;
import cn.mango.community.mapper.QuestionMapper;
import cn.mango.community.mapper.UserMapper;
import cn.mango.community.model.Question;
import cn.mango.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        //获取总数据量，便于后续分页
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;
        //首页全体问题数
        Integer totalCount = questionMapper.count();
        //计算得到全体问题总页数
        if (totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }
        //限制范围，例：共1，2，3页，出现page=-1，仍可跳转
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        //计算页码数据
        paginationDTO.setPagination(totalPage,page);
        //size*(page-1) 真正从第几条数据开始
        Integer offset = size * (page - 1);
        if (offset < 0)
            offset = 1;
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //工具类的作用：快速将前者数据拷贝到后者上
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO  = new PaginationDTO();

        Integer totalPage;
        //个人的问题数
        Integer totalCount = questionMapper.countByUserId(userId);
        //计算得到个人问题总页数
        if (totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }
        //限制范围，例：共1，2，3页，出现page=-1，仍可跳转
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        //计算页码数据
        paginationDTO.setPagination(totalPage, page);

        //size*(page-1) 真正从第几条数据开始
        Integer offset = size * (page - 1);
        if (offset < 0)
            offset = 1;
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //工具类的作用：快速将前者数据拷贝到后者上
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
}











