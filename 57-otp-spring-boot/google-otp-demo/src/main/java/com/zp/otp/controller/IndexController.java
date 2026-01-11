package com.zp.otp.controller;

import com.google.zxing.WriterException;
import com.zp.otp.service.GoogleAuthService;
import com.zp.otp.utils.QrCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zhengpanone
 * Date : 2026/1/11 11:14
 * Version : v1.0.0
 * Description:
 */
@Controller
@RequiredArgsConstructor
public class IndexController {
    private static final Map<String, String> AUTH_KEYS = new HashMap<>();

    @Autowired
    private GoogleAuthService googleAuthService;

    @RequestMapping("/index")
    public String toIndex() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "dashboard";
    }


    @GetMapping("/otp-verification")
    public String optVerification(Model model) {
        return "dashboard";
    }

    @ResponseBody
    @RequestMapping("/getQrCode")
    public String getGoogleAuthQrCode(@RequestParam("username") String username) throws IOException, WriterException {
        String authKey = googleAuthService.genAuthKey();
        AUTH_KEYS.put(username, authKey);
        String authQrCode = googleAuthService.genAuthQrCode(username, authKey);
        byte[] imageBytes = QrCodeUtil.generateQrCode(authQrCode);

        String base64 = Base64.getEncoder().encodeToString(imageBytes);
        return "data:image/png;base64," + base64;
    }

    /**
     * 验证
     *
     * @param username
     * @param code
     * @return
     */
    @RequestMapping("/verify")
    public String verify(@RequestParam("username") String username, @RequestParam("code") String code, RedirectAttributes redirectAttributes) {
        System.out.println("验证用户 " + username + " 的OTP代码: {}" + code);

        // 检查输入是否为空
        if (username == null || username.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "用户名不能为空");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/otp-verification";
        }

        if (code == null || code.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "验证码不能为空");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/otp-verification";
        }

        // 检查验证码格式
        if (!code.matches("\\d{6}")) {
            redirectAttributes.addFlashAttribute("message", "验证码必须是6位数字");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/otp-verification";
        }
        String authKey = AUTH_KEYS.get(username);
        // 检查secret key是否存在
        if (authKey == null || authKey.isEmpty()) {
            System.out.println("用户 {} 的secret key不存在，请先生成二维码" + username);

            redirectAttributes.addFlashAttribute("message", "请先生成绑定二维码");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/otp-verification";
        }

        boolean isValid = googleAuthService.verify(authKey, code);
        if (isValid) {
            redirectAttributes.addFlashAttribute("message", "验证成功!, 正在跳转...");
            redirectAttributes.addFlashAttribute("messageTye", "success");
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("message", "验证失败!, 请重新输入!");
            redirectAttributes.addFlashAttribute("messageTye", "error");
            return "redirect:/otp-verification";
        }
    }
}
