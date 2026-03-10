package com.K.statistic.controller;

import com.K.statistic.bean.GeneralStatistic;
// ⚠️ 引入你新建的 Service
import com.K.statistic.service.OnlineStatisticService;
import com.K.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/statistic/realtime")
public class RealTimeController {

    @GetMapping("revenue/composition")
    public Result<List<GeneralStatistic>> getRevenueComposition(@RequestParam String date) {
        return Result.of(200, "success", OnlineStatisticService.getRevenueComposition(date));
    }
    @GetMapping("gmv")
    public Result<BigDecimal> getGMV(@RequestParam String date) {
        // 👇 改为调用 OnlineStatisticService
        return Result.of(200, "success", OnlineStatisticService.getGMV(date));
    }

    @GetMapping("province")
    public Result<List<GeneralStatistic>> getProvinceStatistic(@RequestParam String date) {
        // 👇 改为调用 OnlineStatisticService
        return Result.of(200, "success", OnlineStatisticService.getProvinceStatistics(date));
    }

    @GetMapping("keyword")
    public Result<List<GeneralStatistic>> getKeywordStatistic(@RequestParam String date) {
        // 👇 改为调用 OnlineStatisticService
        return Result.of(200, "success", OnlineStatisticService.getKeywordStatistics(date));
    }

    @GetMapping("category3")
    public Result<List<GeneralStatistic>> getProductStatsGroupByCategory3(@RequestParam String date) {
        // 👇 改为调用 OnlineStatisticService
        return Result.of(200, "success", OnlineStatisticService.getProductStatisticsGroupByCategory3(date));
    }

    @GetMapping("trademark")
    public Result<List<GeneralStatistic>> getProductStatsByTrademark(@RequestParam String date) {
        // 👇 改为调用 OnlineStatisticService
        return Result.of(200, "success", OnlineStatisticService.getProductStatsByTrademark(date));
    }
}