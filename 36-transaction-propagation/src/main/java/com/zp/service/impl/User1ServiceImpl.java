package com.zp.service.impl;

import com.zp.dao.User1Mapper;
import com.zp.pojo.User1;
import com.zp.service.User1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class User1ServiceImpl implements User1Service {
    @Autowired
    User1Mapper user1Mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User1 user) {
        user1Mapper.insert(user);
    }
}
