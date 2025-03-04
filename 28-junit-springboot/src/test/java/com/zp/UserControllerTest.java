package com.zp;

import com.zp.controller.UserController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletResponse;

import static org.hamcrest.Matchers.equalTo;


/**
 * UserControllerTest
 *
 * @author zhengpanone
 * @since 2022-01-05
 */
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Junit5+springboot controller")
public class UserControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void testUserController() throws Exception {
        RequestBuilder request;

        request = MockMvcRequestBuilders.get("/users/");
        MvcResult mvcResult = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(equalTo("[]")))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        MockHttpServletResponse response =mvcResult.getResponse();
        int status = response.getStatus();
        String content = response.getContentAsString();
        LOGGER.info(status+"\t"+content);

        request = MockMvcRequestBuilders.post("/users/").contentType(MediaType.APPLICATION_JSON)
        .content("{\"id\":1,\"name\":\"testName\",\"age\":18}");
        MvcResult postResult = mvc.perform(request).andExpect(MockMvcResultMatchers.content().string(equalTo("success"))).andReturn();
        LOGGER.info(postResult.toString());

        request = MockMvcRequestBuilders.get("/users/1");
        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(equalTo("{\"id\":1,\"name\":\"testName\",\"age\":18}")));
        LOGGER.info("get by id test pass");

        request = MockMvcRequestBuilders.put("/users/1").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"updateName\",\"age\":30}");
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.content().string(equalTo("success")));
        LOGGER.info("put pass");

        request = MockMvcRequestBuilders.delete("/users/1");
        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(equalTo("success")));



    }

}
