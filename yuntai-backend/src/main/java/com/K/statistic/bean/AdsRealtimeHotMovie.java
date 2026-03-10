package com.K.statistic.bean;


public class AdsRealtimeHotMovie {
    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 电影唯一标识
     */
    private String movieId;
    
    /**
     * 实时热度总分
     */
    private Integer score;
}