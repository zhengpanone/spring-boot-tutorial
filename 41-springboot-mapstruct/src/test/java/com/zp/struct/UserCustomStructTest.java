package com.zp.struct;

import com.zp.dto.UserCustomDTO;
import com.zp.entity.UserDO;
import com.zp.entity.UserExtDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCustomStructTest {

    @Autowired
    private UserCustomStruct userCustomStruct;

    @Test
    public void test1() {
        UserExtDO userExtDO = new UserExtDO();
        userExtDO.setRegSource("公众号：Java技术栈");
        userExtDO.setFavorite("写代码");
        userExtDO.setSchool("社会大学");
        userExtDO.setKids(1);

        UserDO userDO = new UserDO();
        userDO.setName("栈长自定义方法");
        userDO.setSex(1);
        userDO.setAge(18);
        userDO.setBirthday(new Date());
        userDO.setPhone("18888888888");
        userDO.setMarried(true);
        userDO.setRegDate(new Date());
        userDO.setMemo("666");
        userDO.setUserExtDO(userExtDO);

        UserCustomDTO userCustomDTO = userCustomStruct.toUserCustomDTO(userDO);
        System.out.println("=====自定义方法=====");
        System.out.println(userCustomDTO);
    }
}