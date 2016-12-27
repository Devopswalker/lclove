package com.lcpa.lclove.service;

import com.lcpa.lclove.dao.UserMapper;
import com.lcpa.lclove.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shaoheng.huang on 2016/12/27.
 */
@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    public User getUserByName(String userName){
        return userMapper.selectByPrimaryKey(userName);
    }
    public int updatePassword(User user){
        return userMapper.updatePwd(user);
    }

}
