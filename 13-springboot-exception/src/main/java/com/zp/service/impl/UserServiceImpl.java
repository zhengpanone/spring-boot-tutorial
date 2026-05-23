package com.zp.service.impl;

import com.zp.dao.UserMapper;
import com.zp.pojo.User;
import com.zp.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserSerivce {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        this.userMapper.insterUser(user);

    }

    @Override
    public List<User> getAllUser() {
        return userMapper.selectUserAll();
    }

    @Override
    public User findUserById(Integer id) {

        return this.userMapper.findUserById(id);
    }

    @Override
    public void updateUser(User user) {
        this.userMapper.updateUser(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        this.userMapper.deleteUserById(id);
    }
}
