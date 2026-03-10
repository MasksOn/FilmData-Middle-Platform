package com.K.statistic.bean;

import java.math.BigDecimal;
import java.util.Date;

public class AdsUserSegmentation {
    private String userId;
    private Long totalRatedMovies;
    private BigDecimal avgGivenRating;
    private BigDecimal ratingStddev;
    private Date firstActiveDate;
    private Date lastActiveDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getTotalRatedMovies() {
        return totalRatedMovies;
    }

    public void setTotalRatedMovies(Long totalRatedMovies) {
        this.totalRatedMovies = totalRatedMovies;
    }

    public BigDecimal getAvgGivenRating() {
        return avgGivenRating;
    }

    public void setAvgGivenRating(BigDecimal avgGivenRating) {
        this.avgGivenRating = avgGivenRating;
    }

    public BigDecimal getRatingStddev() {
        return ratingStddev;
    }

    public void setRatingStddev(BigDecimal ratingStddev) {
        this.ratingStddev = ratingStddev;
    }

    public Date getFirstActiveDate() {
        return firstActiveDate;
    }

    public void setFirstActiveDate(Date firstActiveDate) {
        this.firstActiveDate = firstActiveDate;
    }

    public Date getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(Date lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }
}