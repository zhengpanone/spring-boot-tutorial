package com.zp.service;

import com.zp.pojo.User;

import java.util.List;

public interface UserSerivce {

    void addUser(User user);

    List<User> getAllUser();

    User findUserById(Integer id);

    void updateUser(User user);

    void deleteUserById(Integer id);
}
