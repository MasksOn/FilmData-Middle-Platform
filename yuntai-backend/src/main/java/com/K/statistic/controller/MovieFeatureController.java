package com.K.statistic.controller;

import com.K.statistic.bean.AdsMovieRecommendation;
import com.K.statistic.bean.AdsUserSegmentation;
import com.K.statistic.bean.Page;
import com.K.statistic.service.AdsStatisticService;
import com.K.util.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistic/feature")
public class MovieFeatureController {

    @GetMapping("movie/list/{page}/{limit}")
    public Result<Page<AdsMovieRecommendation>> getMovieList(
            @PathVariable Integer page,
            @PathVariable Integer limit,
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = "") String genre) { // 👈 新增 genre 参数

        Page<AdsMovieRecommendation> result = AdsStatisticService.getMovieRecommendationList(page, limit, keyword, genre);
        return Result.of(200, "success", result);
    }
    @GetMapping("user/list/{page}/{limit}")
    public Result<Page<AdsUserSegmentation>> getUserList(
            @PathVariable Integer page,
            @PathVariable Integer limit,
            @RequestParam(required = false, defaultValue = "") String keyword) {

        Page<AdsUserSegmentation> result = AdsStatisticService.getUserSegmentationList(page, limit, keyword);
        return Result.of(200, "success", result);
    }
}