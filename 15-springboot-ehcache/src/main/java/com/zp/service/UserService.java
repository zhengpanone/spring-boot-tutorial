package com.zp.service;

import com.zp.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<User> findAllUser();

    User findUserById(Integer id);

    Page<User> findUserByPage(Pageable pageable);

}
