package com.zp.sign.controller;

import com.zp.sign.service.SignService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sign")
@AllArgsConstructor
public class SignController {

    private final SignService signService;

    @PostMapping("/{userId}")
    public String sign(@PathVariable String userId,
                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        if (Objects.isNull(date)) date = LocalDate.now();
        boolean result = signService.sign(userId, date);
        return result ? "签到成功" : "签到失败或已签到";
    }

    @PostMapping("/{userId}/retroactive")
    public String retroactiveSign(@PathVariable String userId,
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        boolean result = signService.retroactiveSign(userId, date);
        return result ? "补签成功" : "补签失败";
    }

    @GetMapping("/{userId}/check")
    public boolean checkSign(@PathVariable String userId,
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return signService.checkSign(userId, date);
    }

    @GetMapping("/{userId}/count")
    public long getSingCount(@PathVariable String userId,
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return signService.getSignCount(userId, date);
    }

    @GetMapping("/{userId}/continuous")
    public long getContinuousSignCount(@PathVariable String userId,
                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return signService.getContinuousSignCount(userId, date);
    }

    @GetMapping("/{userId}/info")
    public List<Boolean> getSignInfo(@PathVariable String userId,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        return signService.getSignInfo(userId, date);
    }


}
