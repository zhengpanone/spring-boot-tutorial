package com.zp.sign.service;


import java.time.LocalDate;
import java.util.List;

public interface SignService {

    /**
     * 用户签到
     *
     * @param userId 用户 ID
     * @param date   签到日期
     * @return 是否签到成功
     */
    boolean sign(String userId, LocalDate date);

    /**
     * 用户补签
     *
     * @param userId 用户 ID
     * @param date   补签日期
     * @return 是否补签成功
     */
    boolean retroactiveSign(String userId, LocalDate date);

    /**
     * 检查某日是否签到
     *
     * @param userId 用户 ID
     * @param date   签到日期
     * @return 是否签到
     */
    boolean checkSign(String userId, LocalDate date);

    /**
     * 获取当月签到次数
     *
     * @param userId 用户 ID
     * @param date   年月份
     * @return 签到次数
     */
    long getSignCount(String userId, LocalDate date);

    /**
     * 获取连续签到天数 (支持跨月)
     *
     * @param userId 用户 ID
     * @param date   年月份
     * @return 签到次数
     */
    long getContinuousSignCount(String userId, LocalDate date);

    /**
     * 获取当月签到详情
     *
     * @param userId 用户 ID
     * @param date   年月份
     * @return 是否签到列表
     */
    List<Boolean> getSignInfo(String userId, LocalDate date);
}
