package com.K.statistic.controller;

import com.K.statistic.bean.*;
import com.K.statistic.service.AdsStatisticService;
import com.K.util.Result;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/statistic/trend")
public class MarketTrendController {

    // 1. 年代与类型演进堆叠图
    @GetMapping("decade")
    public Result<List<AdsDecadeGenreTrend>> getDecadeTrend(@RequestParam String date) {
        return Result.of(200, "success", AdsStatisticService.getDecadeGenreTrend(date));
    }

    // 2. 电影类型受众大盘 (玫瑰图)
    @GetMapping("genre-popular")
    public Result<List<AdsGenrePopularStat>> getGenrePopular(@RequestParam String date) {
        return Result.of(200, "success", AdsStatisticService.getGenrePopularStat(date));
    }
}