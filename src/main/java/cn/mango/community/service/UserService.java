package cn.mango.community.service;

import cn.mango.community.mapper.UserMapper;
import cn.mango.community.model.User;
import cn.mango.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);

        //判断现登录的accountId与数据库中是否有匹配
        if (users.size() == 0){
            //插入时的时间
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            //插入
            userMapper.insert(user);
        }else{
            //更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            //获取id
            example.createCriteria()
                            .andIdEqualTo(dbUser.getId());
            //updateByExampleSelective 查询用法可知，根据需要更新所需内容
            userMapper.updateByExampleSelective(updateUser,example);
        }
    }
}
