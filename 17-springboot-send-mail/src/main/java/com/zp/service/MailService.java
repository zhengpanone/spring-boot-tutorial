package com.zp.service;

import com.zp.vo.MailVo;

public interface MailService {
    /**
     * 发送邮件
     *
     * @param mailVo
     * @return
     */
    MailVo sendMail(MailVo mailVo);

    /**
     * 检测邮件信息类
     *
     * @param mailVo
     */
    void checkMail(MailVo mailVo);

    /**
     * 构建复杂邮件信息类
     *
     * @param mailVo
     */
    void sendMimeMail(MailVo mailVo);

    /**
     * 保存邮件
     *
     * @param mailVo
     * @return
     */
    MailVo saveMail(MailVo mailVo);

    /**
     * 获取邮件发件人
     *
     * @return
     */
    String getMailSendFrom();
}
