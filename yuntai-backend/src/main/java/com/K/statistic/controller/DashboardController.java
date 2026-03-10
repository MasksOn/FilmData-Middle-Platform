package com.K.statistic.controller;

import com.K.statistic.service.AdsStatisticService;
import com.K.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin/dashboard")
public class DashboardController {

    /**
     * 获取首页卡片基础统计数据
     */
    @GetMapping("/base-stats")
    public Result<Map<String, Object>> getBaseStats() {
        // 调用我们刚刚写好的 Service 方法，直接查 MySQL
        Map<String, Object> stats = AdsStatisticService.getDashboardBaseStats();
        return Result.of(200, "success", stats);
    }
}