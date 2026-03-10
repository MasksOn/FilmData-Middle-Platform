package com.K.statistic.bean;

import java.math.BigDecimal;
import java.util.Date;

public class AdsMovieRecommendation {
    private String movieId;
    private String title;
    private Integer releaseYear;
    private String genres;
    private Long totalRatingCount;
    private BigDecimal avgRating;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Long getTotalRatingCount() {
        return totalRatingCount;
    }

    public void setTotalRatingCount(Long totalRatingCount) {
        this.totalRatingCount = totalRatingCount;
    }

    public BigDecimal getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(BigDecimal avgRating) {
        this.avgRating = avgRating;
    }

    public Long getFiveStarCount() {
        return fiveStarCount;
    }

    public void setFiveStarCount(Long fiveStarCount) {
        this.fiveStarCount = fiveStarCount;
    }

    public Date getLatestRatingTime() {
        return latestRatingTime;
    }

    public void setLatestRatingTime(Date latestRatingTime) {
        this.latestRatingTime = latestRatingTime;
    }

    private Long fiveStarCount;
    private Date latestRatingTime;
}