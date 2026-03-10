package com.K.recommend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RecommendService {

    // 实例化 RestTemplate 用于微服务 HTTP 通信
    private final RestTemplate restTemplate = new RestTemplate();
    
    // ⚠️ 你的 Python 微服务基础地址 (注意与 Python FastAPI 的路由保持一致)
    private static final String PYTHON_ALGORITHM_URL = "http://localhost:8000/internal/recommend/movies/";

    /**
     * 将请求转发给 Python 计算引擎
     */
    public Map<String, Object> fetchRecommendationFromPython(String userId, String algorithm, Integer topN) {
        // 拼接发往 Python 8000 端口的完整 URL
        // 格式: http://localhost:8000/internal/recommend/movies/1024?algorithm=item_cf&top_n=6
        String targetUrl = PYTHON_ALGORITHM_URL + userId 
                + "?algorithm=" + algorithm 
                + "&top_n=" + topN;

        try {
            // 发送 GET 请求，并期望 Python 返回一个 JSON（在 Java 中被解析为 Map）
            return restTemplate.getForObject(targetUrl, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("无法连接到 Python 推荐引擎服务 (8000端口): " + e.getMessage());
        }
    }
}