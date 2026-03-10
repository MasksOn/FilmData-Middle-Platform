package com.K.recommend.bean;

/**
 * 接收 Vue 前端传来的推荐请求参数
 */
public class RecommendQuery {
    private String userId;
    private String algorithm;
    private Integer topN;

    // Getter 和 Setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Integer getTopN() {
        return topN;
    }

    public void setTopN(Integer topN) {
        this.topN = topN;
    }
}