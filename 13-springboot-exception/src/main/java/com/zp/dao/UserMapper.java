package com.zp.dao;

import com.zp.pojo.User;

import java.util.List;

public interface UserMapper {

    void insterUser(User user);

    List<User> selectUserAll();

    User findUserById(Integer id);

    void updateUser(User user);

    void deleteUserById(Integer id);
}
