package com.K.statistic.controller;

import com.K.statistic.bean.Page;
import com.K.statistic.bean.TrafficStatistic;
import com.K.statistic.service.StatisticService;
import com.K.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/statistic/visit")
public class TrafficController {
    @GetMapping("getTrafficStats/{page}/{limit}")
    public Result<Page<TrafficStatistic>> getTrafficStats(@PathVariable Integer page, @PathVariable Integer limit, TrafficStatistic trafficStatistic) {
        var trafficStatistics = StatisticService.getTrafficStatistics(page, limit, trafficStatistic.getDt(), trafficStatistic.getRecentDays());
        return Result.of(200, "success", trafficStatistics);
    }

    /**
     * 用户路径分析
     */
    @GetMapping("getPagePath")
    public Result<Map<String, Object>> getPagePath(TrafficStatistic trafficStatistic) {
        return Result.of(200, "success", StatisticService.getPagePathCount(trafficStatistic));
    }
    @GetMapping("/admin/dashboard/base-stats")
    public Result<Map<String, Object>> getBaseStats() {
        Map<String, Object> data = new HashMap<>();
        // 这里你可以写真实的 SQL 去 MySQL 里查 COUNT(*)，目前写死做演示：
        data.put("userCount", 1888);
        data.put("visitCount", 9527);
        return Result.of(200, "success", data);
    }
}
