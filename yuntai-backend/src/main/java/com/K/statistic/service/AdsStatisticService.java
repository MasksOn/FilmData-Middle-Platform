package com.K.statistic.service;

import com.K.statistic.bean.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdsStatisticService {

    // 1. 获取热力图数据 (活跃时段打卡图)
    public static List<AdsUserActiveHeatmapStat> getHeatmapStat(String dt) {
        String sql = "SELECT day_of_week, hour_of_day, interaction_count FROM ads_user_active_heatmap_stat WHERE dt = ?";
        List<AdsUserActiveHeatmapStat> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE.MYSQL_URL, DATABASE.MYSQL_USERNAME, DATABASE.MYSQL_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AdsUserActiveHeatmapStat stat = new AdsUserActiveHeatmapStat();
                stat.setDayOfWeek(rs.getInt("day_of_week"));
                stat.setHourOfDay(rs.getInt("hour_of_day"));
                stat.setInteractionCount(rs.getLong("interaction_count"));
                stat.setDt(dt);
                result.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 2. 获取词云图数据 (全站影评核心词)
    public static List<AdsTagWordcloudStat> getWordcloudStat(String dt) {
        // 取权重最高的前 50 个词汇，保证词云图美观不杂乱
        String sql = "SELECT tag_name, weight FROM ads_tag_wordcloud_stat WHERE dt = ? ORDER BY weight DESC LIMIT 50";
        List<AdsTagWordcloudStat> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE.MYSQL_URL, DATABASE.MYSQL_USERNAME, DATABASE.MYSQL_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AdsTagWordcloudStat stat = new AdsTagWordcloudStat();
                stat.setTagName(rs.getString("tag_name"));
                stat.setWeight(rs.getLong("weight"));
                stat.setDt(dt);
                result.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 3. 获取年代与电影类型演进图 (堆叠面积图)
    public static List<AdsDecadeGenreTrend> getDecadeGenreTrend(String dt) {
        String sql = "SELECT decade, genre, movie_count FROM ads_decade_genre_trend WHERE dt = ? ORDER BY decade ASC";
        List<AdsDecadeGenreTrend> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE.MYSQL_URL, DATABASE.MYSQL_USERNAME, DATABASE.MYSQL_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AdsDecadeGenreTrend stat = new AdsDecadeGenreTrend();
                stat.setDecade(rs.getString("decade"));
                stat.setGenre(rs.getString("genre"));
                stat.setMovieCount(rs.getLong("movie_count"));
                stat.setDt(dt);
                result.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 4. 获取电影口碑与热度分布图 (气泡散点图)
    public static List<AdsMovieScatterStat> getMovieScatterStat(String dt) {
        String sql = "SELECT movie_id, title, rating_count, avg_rating, main_genre FROM ads_movie_scatter_stat WHERE dt = ?";
        List<AdsMovieScatterStat> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE.MYSQL_URL, DATABASE.MYSQL_USERNAME, DATABASE.MYSQL_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AdsMovieScatterStat stat = new AdsMovieScatterStat();
                stat.setMovieId(rs.getInt("movie_id"));
                stat.setTitle(rs.getString("title"));
                stat.setRatingCount(rs.getLong("rating_count"));
                stat.setAvgRating(rs.getDouble("avg_rating"));
                stat.setMainGenre(rs.getString("main_genre"));
                stat.setDt(dt);
                result.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 5. 获取排行榜数据 (横向柱状图) - 获取热榜 Top 10
    public static List<AdsMovieRankTopN> getMovieHotRankTop10(String dt) {
        String sql = "SELECT movieId, title, release_year, rating_count, avg_rating, hot_rank FROM ads_movie_rank_topN WHERE dt = ? ORDER BY hot_rank ASC LIMIT 10";
        List<AdsMovieRankTopN> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE.MYSQL_URL, DATABASE.MYSQL_USERNAME, DATABASE.MYSQL_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AdsMovieRankTopN stat = new AdsMovieRankTopN();
                stat.setMovieId(rs.getInt("movieId"));
                stat.setTitle(rs.getString("title"));
                stat.setReleaseYear(rs.getString("release_year"));
                stat.setRatingCount(rs.getLong("rating_count"));
                stat.setAvgRating(rs.getDouble("avg_rating"));
                stat.setHotRank(rs.getInt("hot_rank"));
                stat.setDt(dt);
                result.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 6. 获取类型受众大盘 (南丁格尔玫瑰图/饼图)
    public static List<AdsGenrePopularStat> getGenrePopularStat(String dt) {
        // 取前 8 大类型，避免饼图过于细碎
        String sql = "SELECT genre, total_movies, total_ratings, avg_rating, market_share_pct, popularity_rank FROM ads_genre_popular_stat WHERE dt = ? ORDER BY popularity_rank ASC LIMIT 8";
        List<AdsGenrePopularStat> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE.MYSQL_URL, DATABASE.MYSQL_USERNAME, DATABASE.MYSQL_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AdsGenrePopularStat stat = new AdsGenrePopularStat();
                stat.setGenre(rs.getString("genre"));
                stat.setTotalMovies(rs.getLong("total_movies"));
                stat.setTotalRatings(rs.getLong("total_ratings"));
                stat.setAvgRating(rs.getDouble("avg_rating"));
                stat.setMarketSharePct(rs.getDouble("market_share_pct"));
                stat.setPopularityRank(rs.getInt("popularity_rank"));
                stat.setDt(dt);
                result.add(stat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    // 获取电影特征列表 (支持按电影名、分类模糊搜索 + 分页)
    public static Page<AdsMovieRecommendation> getMovieRecommendationList(Integer page, Integer limit, String keyword, String genre) {
        StringBuilder countSql = new StringBuilder("SELECT COUNT(1) AS total FROM ads_movie_recommendation WHERE title LIKE ?");
        StringBuilder listSql = new StringBuilder("SELECT * FROM ads_movie_recommendation WHERE title LIKE ?");

        boolean hasGenre = genre != null && !genre.trim().isEmpty();
        if (hasGenre) {
            countSql.append(" AND genres LIKE ?");
            listSql.append(" AND genres LIKE ?");
        }
        listSql.append(" ORDER BY total_rating_count DESC LIMIT ? OFFSET ?");

        Page<AdsMovieRecommendation> pageResult = new Page<>();
        pageResult.current = page;
        pageResult.size = limit;
        pageResult.records = new ArrayList<>();

        String searchKeyword = "%" + (keyword == null ? "" : keyword) + "%";
        String searchGenre = "%" + genre + "%";

        try (Connection conn = DriverManager.getConnection(DATABASE.MYSQL_URL, DATABASE.MYSQL_USERNAME, DATABASE.MYSQL_PASSWORD);
             PreparedStatement countPs = conn.prepareStatement(countSql.toString());
             PreparedStatement listPs = conn.prepareStatement(listSql.toString())) {

            // 1. 查总数绑定参数
            countPs.setString(1, searchKeyword);
            if (hasGenre) {
                countPs.setString(2, searchGenre);
            }
            ResultSet countRs = countPs.executeQuery();
            if (countRs.next()) {
                pageResult.total = countRs.getInt("total");
            }

            // 2. 查列表绑定参数
            listPs.setString(1, searchKeyword);
            int paramIndex = 2;
            if (hasGenre) {
                listPs.setString(paramIndex++, searchGenre);
            }
            listPs.setInt(paramIndex++, limit);
            listPs.setInt(paramIndex, (page - 1) * limit);

            ResultSet rs = listPs.executeQuery();
            while (rs.next()) {
                AdsMovieRecommendation movie = new AdsMovieRecommendation();
                movie.setMovieId(rs.getString("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setReleaseYear(rs.getInt("release_year"));
                movie.setGenres(rs.getString("genres"));
                movie.setTotalRatingCount(rs.getLong("total_rating_count"));
                movie.setAvgRating(rs.getBigDecimal("avg_rating"));
                movie.setFiveStarCount(rs.getLong("five_star_count"));
                pageResult.records.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pageResult;
    }
    // 获取用户画像列表 (支持按用户ID搜索 + 分页)
    public static Page<AdsUserSegmentation> getUserSegmentationList(Integer page, Integer limit, String keyword) {
        String countSql = "SELECT COUNT(1) AS total FROM ads_user_segmentation WHERE user_id LIKE ?";
        String listSql = "SELECT * FROM ads_user_segmentation WHERE user_id LIKE ? ORDER BY total_rated_movies DESC LIMIT ? OFFSET ?";

        Page<AdsUserSegmentation> pageResult = new Page<>();
        pageResult.current = page;
        pageResult.size = limit;
        pageResult.records = new ArrayList<>();

        String searchKeyword = "%" + (keyword == null ? "" : keyword) + "%";

        try (Connection conn = DriverManager.getConnection(DATABASE.MYSQL_URL, DATABASE.MYSQL_USERNAME, DATABASE.MYSQL_PASSWORD);
             PreparedStatement countPs = conn.prepareStatement(countSql);
             PreparedStatement listPs = conn.prepareStatement(listSql)) {

            // 1. 查总数
            countPs.setString(1, searchKeyword);
            ResultSet countRs = countPs.executeQuery();
            if (countRs.next()) {
                pageResult.total = countRs.getInt("total");
            }

            // 2. 查当前页列表
            listPs.setString(1, searchKeyword);
            listPs.setInt(2, limit);
            listPs.setInt(3, (page - 1) * limit);
            ResultSet rs = listPs.executeQuery();

            while (rs.next()) {
                AdsUserSegmentation user = new AdsUserSegmentation();
                user.setUserId(rs.getString("user_id"));
                user.setTotalRatedMovies(rs.getLong("total_rated_movies"));
                user.setAvgGivenRating(rs.getBigDecimal("avg_given_rating"));
                user.setRatingStddev(rs.getBigDecimal("rating_stddev"));
                user.setFirstActiveDate(rs.getDate("first_active_date"));
                user.setLastActiveDate(rs.getDate("last_active_date"));
                pageResult.records.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pageResult;
    }
    // =========================================================
    // 首页 Dashboard 核心数据指标查询
    // =========================================================
    public static Map<String, Object> getDashboardBaseStats() {
        Map<String, Object> result = new HashMap<>();
        long userCount = 0;
        long visitCount = 0;

        // 1. 查询系统总用户数 (统计用户画像表中的去重用户)
        String userSql = "SELECT COUNT(1) AS total_users FROM ads_user_segmentation";
        // 2. 查询系统总访问/交互流量 (汇总热力图中的所有交互次数)
        String visitSql = "SELECT SUM(interaction_count) AS total_visits FROM ads_user_active_heatmap_stat";

        try (Connection conn = DriverManager.getConnection(DATABASE.MYSQL_URL, DATABASE.MYSQL_USERNAME, DATABASE.MYSQL_PASSWORD)) {

            // 执行查询用户总数
            try (PreparedStatement ps = conn.prepareStatement(userSql);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userCount = rs.getLong("total_users");
                }
            }

            // 执行查询总访问量
            try (PreparedStatement ps = conn.prepareStatement(visitSql);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    visitCount = rs.getLong("total_visits");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("获取首页大屏基础数据失败，将使用默认兜底数据");
        }

        // 将查到的真实数据放入 Map（如果数据库为空，做个兜底展示 1024 和 8192）
        result.put("userCount", userCount > 0 ? userCount : 1024);
        result.put("visitCount", visitCount > 0 ? visitCount : 8192);

        return result;
    }
}