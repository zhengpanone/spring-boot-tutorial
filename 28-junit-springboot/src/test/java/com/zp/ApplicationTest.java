package com.zp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ApplicationTest
 *
 * @author zhengpanone
 * @since 2021-12-15
 */
@SpringBootTest
public class ApplicationTest {
    /**
     * 每个单元测试执行前执行
     */
    @BeforeEach
    void beforeEach() {
        System.out.println("method beforeEach executed");
    }

    /**
     * 每个单元测试执行后执行
     */
    @AfterEach
    void afterEach() {
        System.out.println("method afterEach executed");
    }

    /**
     * 所有单元测试方法执行前执行
     */
    @BeforeAll
    static void beforeAll() {
        System.out.println("method beforeAll executed");
    }

    /**
     * 所有单元测试方法执行后执行
     */
    @AfterAll
    static void afterAll() {
        System.out.println("method afterAll executed");
    }

    /**
     * 标记为测试方法
     */
    @Test
    void tes1() {
        Assertions.assertTrue(true);
    }

    /**
     * 参数化测试
     *
     * @param value
     */
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100})
    void test2(int value) {
        Assertions.assertTrue(value > 0 && value <= 100);
    }

    /**
     * 重复测试
     */
    @RepeatedTest(5)
    void test3() {
        Assertions.assertTrue(true);
    }

    /**
     * 为测试类或方法设置别名
     */
    @Test
    @DisplayName("Junit5 Test Demo")
    void test4() {
        Assertions.assertTrue(true);
    }
}
