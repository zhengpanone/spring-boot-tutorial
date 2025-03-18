package com.zp.controller.form;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * LoginForm
 *
 * @author zhengpanone
 * @since 2022-09-20
 */
@Data
public class LoginForm {
    @NotNull(message = "用户名不能为空")
    private String userName;
    @NotNull(message = "密码不能为空")
    private String password;
}
