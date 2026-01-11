package com.zp.sign.service.impl;

import com.zp.sign.service.SignService;
import lombok.AllArgsConstructor;
import org.redisson.api.RBitSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class SignServiceImpl implements SignService {

    private final RedissonClient redissonClient;
    private static final DateTimeFormatter KEY_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");

    @Override
    public boolean sign(String userId, LocalDate date) {
        String key = buildKey(userId, date);
        int offset = date.getDayOfMonth() - 1;
        RBitSet bitSet = redissonClient.getBitSet(key);
        if (bitSet.get(offset)) {
            return false; // 已签到
        }
        bitSet.set(offset);
        return true;
    }

    @Override
    public boolean retroactiveSign(String userId, LocalDate date) {
        return sign(userId, date); // 与签到逻辑一致
    }

    @Override
    public boolean checkSign(String userId, LocalDate date) {
        String key = buildKey(userId, date);
        int offset = date.getDayOfMonth() - 1;
        RBitSet bitSet = redissonClient.getBitSet(key);
        return bitSet.get(offset);
    }

    @Override
    public long getSignCount(String userId, LocalDate date) {
        String key = buildKey(userId, date);
        RBitSet bitSet = redissonClient.getBitSet(key);
        return bitSet.cardinality();
    }


    @Override
    public long getContinuousSignCount(String userId, LocalDate date) {
        int count = 0;
        LocalDate current = date;
        while (true) {
            String key = buildKey(userId, current);
            RBitSet bitSet = redissonClient.getBitSet(key);

            int day = current.getDayOfMonth() - 1;
            boolean signed = bitSet.get(day);
            if (!signed) break;
            count++;
            current = current.minusDays(1);
            if (current.getDayOfMonth() == current.lengthOfMonth()) {
                // 跨月继续
                continue;
            }
        }
        return count;
    }

    @Override
    public List<Boolean> getSignInfo(String userId, LocalDate date) {
        String key = buildKey(userId, date);
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();

        RBitSet bitSet = redissonClient.getBitSet(key);
        return IntStream.range(0, daysInMonth).mapToObj(bitSet::get).collect(Collectors.toList());
    }

    /**
     * 构建 redis key
     *
     * @param userId 用户 ID
     * @param date   日期
     * @return redis key
     */
    private String buildKey(String userId, LocalDate date) {
        return String.format("sign:%s:%s", userId, date.format(KEY_FORMATTER));
    }
}
