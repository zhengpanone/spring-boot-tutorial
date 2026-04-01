package com.zp.otp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

/**
 * @author : zhengpanone
 * Date : 2026/1/11 11:25
 * Version : v1.0.0
 * Description:
 */
@SpringBootApplication
public class GoogleOTPApplication {
    public static void main(String[] args) {
        checkTemplates();
        SpringApplication.run(GoogleOTPApplication.class, args);
    }

    private static void checkTemplates() {
        System.out.println("=== 检查模板文件 ===");
        String[] templates = {
                "templates/index.html",
                "templates/otp-verification.html"
        };

        for (String path : templates) {
            Resource resource = new ClassPathResource(path);
            try {
                boolean exists = resource.exists();
                System.out.println(path + ": " + (exists ? "✓ 存在" : "✗ 不存在"));
                if (exists) {
                    System.out.println("   位置: " + resource.getURL());
                }
            } catch (IOException e) {
                System.out.println(path + ": ✗ 检查失败 - " + e.getMessage());
            }
        }
        System.out.println("=== 检查结束 ===");
    }
}
