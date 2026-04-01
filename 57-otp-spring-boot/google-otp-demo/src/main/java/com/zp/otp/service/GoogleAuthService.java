package com.zp.otp.service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import org.springframework.stereotype.Component;

/**
 * @author : zhengpanone
 * Date : 2026/1/11 11:08
 * Version : v1.0.0
 * Description:
 */
@Component
public class GoogleAuthService {

    /**
     * 生成secretKey
     *
     * @return
     */
    public String genAuthKey() {
        GoogleAuthenticator authenticator = new GoogleAuthenticator();
        return authenticator.createCredentials().getKey();
    }

    /**
     * 生成绑定Google Authenticator 二维码URL
     *
     * @param username
     * @param authKey
     * @return
     */
    public String genAuthQrCode(String username, String authKey) {
        return GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL("google-otp-demo", username,
                new GoogleAuthenticatorKey.Builder(authKey).build());
    }

    /**
     * 验证
     * @param authKey
     * @param code
     * @return
     */
    public boolean verify(String authKey, String code) {
        GoogleAuthenticator authenticator = new GoogleAuthenticator();
        return authenticator.authorize(authKey, Integer.parseInt(code));
    }
}
