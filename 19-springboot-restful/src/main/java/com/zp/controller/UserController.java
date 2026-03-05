package com.zp.controller;

import com.zp.controller.form.LoginForm;
import com.zp.controller.vo.UserVO;
import com.zp.response.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * UserController
 *
 * @author zhengpanone
 * @since 2022-09-20
 */
@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Value("${app.version:unknown}")
    private String version;

    @Value("${HOSTNAME:unknown}")
    private String hostname;

    @PostMapping("/user/login")
    public ApiResult<UserVO> userGet(@Validated @RequestBody LoginForm loginForm, BindingResult bindingResult) {
       if(bindingResult.hasErrors()){
           return ApiResult.failed(bindingResult.getFieldError().getDefaultMessage());
       }
        LOGGER.info("提交的用户数据为：" + loginForm.toString());
        UserVO userVO = new UserVO();
        userVO.setUserId(1);
        userVO.setUserName(loginForm.getUserName());
        userVO.setUserLevel(10);
        return ApiResult.success(userVO);
    }


    @GetMapping("/hello")
    public ApiResult<Map<String, Object>> hello() {

        Map<String, Object> result = new HashMap<>();
        result.put("message", "Hello Gateway API + Spring Boot");
        result.put("version", version);
        result.put("pod", hostname);

        return ApiResult.success(result);
    }
}
