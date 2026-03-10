package com.K.statistic.bean;

public class AdsMovieRankTopN {
    private Integer movieId;
    private String title;
    private String releaseYear;
    private Long ratingCount;
    private Double avgRating;
    private Integer scoreRank;
    private Integer hotRank;
    private String dt;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Long getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Long ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public Integer getScoreRank() {
        return scoreRank;
    }

    public void setScoreRank(Integer scoreRank) {
        this.scoreRank = scoreRank;
    }

    public Integer getHotRank() {
        return hotRank;
    }

    public void setHotRank(Integer hotRank) {
        this.hotRank = hotRank;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}