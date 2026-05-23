package com.zp.dao;

import com.zp.pojo.User1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface User1Mapper {
    int insert(@Param("user1") User1 record);
    User1 selectByPrimaryKey(@Param("id") Integer id);
}
