package com.K.statistic.controller;

import com.K.statistic.bean.*;
import com.K.statistic.service.AdsStatisticService;
import com.K.util.Result;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/statistic/insight")
public class AdvancedInsightController {

    // 1. 核心词云图
    @GetMapping("wordcloud")
    public Result<List<AdsTagWordcloudStat>> getWordcloud(@RequestParam String date) {
        return Result.of(200, "success", AdsStatisticService.getWordcloudStat(date));
    }

    // 2. 口碑与热度分布气泡散点图
    @GetMapping("scatter")
    public Result<List<AdsMovieScatterStat>> getScatter(@RequestParam String date) {
        return Result.of(200, "success", AdsStatisticService.getMovieScatterStat(date));
    }
}