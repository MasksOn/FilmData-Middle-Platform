package com.K.statistic.controller;

import com.K.statistic.bean.*;
import com.K.statistic.service.AdsStatisticService;
import com.K.util.Result;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/statistic/monitor")
public class RealtimeMonitorController {

    // 1. 活跃打卡热力图
    @GetMapping("heatmap")
    public Result<List<AdsUserActiveHeatmapStat>> getHeatmap(@RequestParam String date) {
        return Result.of(200, "success", AdsStatisticService.getHeatmapStat(date));
    }

    // 2. 实时热播排行榜 TOP 10
    @GetMapping("hot-rank")
    public Result<List<AdsMovieRankTopN>> getHotRankTop10(@RequestParam String date) {
        return Result.of(200, "success", AdsStatisticService.getMovieHotRankTop10(date));
    }
}