package com.K.recommend.controller;

import com.K.recommend.bean.RecommendQuery;
import com.K.recommend.service.RecommendService;
import com.K.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    /**
     * 对应前端 report.js 中的请求: GET /recommend/movies
     */
    @GetMapping("/movies")
    public Result<Map<String, Object>> getMovieRecommendation(RecommendQuery query) {
        
        // 1. 参数校验防御 (避免前端传空)
        if (query.getUserId() == null || query.getUserId().trim().isEmpty()) {
            return Result.of(400, "用户 ID 不能为空", null);
        }
        
        // 设置默认值保护
        String algorithm = query.getAlgorithm() != null ? query.getAlgorithm() : "item_cf";
        Integer topN = query.getTopN() != null ? query.getTopN() : 6;

        try {
            // 2. 调用 Service 转发请求给 Python
            Map<String, Object> pyResponse = recommendService.fetchRecommendationFromPython(
                    query.getUserId(), algorithm, topN);

            // 3. 解析 Python 传回的 JSON 状态，包装成 Vue 能理解的 Result
            if (pyResponse != null && "success".equals(pyResponse.get("status"))) {
                return Result.of(200, "算法预测成功", pyResponse);
            } else {
                String errorMsg = pyResponse != null ? (String) pyResponse.get("message") : "Python 服务未返回明确错误";
                return Result.of(500, "Python 引擎计算异常: " + errorMsg, null);
            }

        } catch (Exception e) {
            return Result.of(500, "微服务通信失败: " + e.getMessage(), null);
        }
    }
}