package com.zp.service.impl;

import com.zp.dao.UserRepository;
import com.zp.pojo.User;
import com.zp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        return user;
    }

    @Override
    public Page<User> findUserByPage(Pageable pageable) {
        return null;
    }
}
