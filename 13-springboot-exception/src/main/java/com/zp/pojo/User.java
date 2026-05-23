package com.zp.pojo;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


public class User {

    private Integer id;
    @NotBlank(message = "用户名不能为空")
    @Length(min = 6, max = 12)
    private String name;
    @NotBlank(message = "密码不能为空")
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
