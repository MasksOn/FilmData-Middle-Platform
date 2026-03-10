package com.K.statistic.bean;

public class AdsGenrePopularStat {
    private String genre;
    private Long totalMovies;
    private Long totalRatings;
    private Double avgRating;
    private Double marketSharePct;
    private Integer popularityRank;
    private String dt;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getTotalMovies() {
        return totalMovies;
    }

    public void setTotalMovies(Long totalMovies) {
        this.totalMovies = totalMovies;
    }

    public Long getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(Long totalRatings) {
        this.totalRatings = totalRatings;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public Double getMarketSharePct() {
        return marketSharePct;
    }

    public void setMarketSharePct(Double marketSharePct) {
        this.marketSharePct = marketSharePct;
    }

    public Integer getPopularityRank() {
        return popularityRank;
    }

    public void setPopularityRank(Integer popularityRank) {
        this.popularityRank = popularityRank;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }
}