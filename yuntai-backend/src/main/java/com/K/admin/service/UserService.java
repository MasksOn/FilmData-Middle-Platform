package com.K.admin.service;

import com.K.admin.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {
    /**
     * 获取所有用户信息
     */
    public static List<User> getAllUsers() {
        try {
            Class.forName(DATABASE.DRIVER);
            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);
            // 修改点：补充查询新增的字段
            var selectStatement = connection.prepareStatement(
                    "SELECT id, username, password, email, tel, last_login, status FROM user"
            );
            var resultSet = selectStatement.executeQuery();
            var users = new ArrayList<User>();
            while (resultSet.next()) {
                var user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));

                // 修改点：映射新增字段
                user.setEmail(resultSet.getString("email"));
                user.setTel(resultSet.getString("tel"));
                user.setStatus(resultSet.getInt("status")); // status 改为了 Integer

                Timestamp lastLogin = resultSet.getTimestamp("last_login");
                if (lastLogin != null) {
                    user.setLastLogin(new java.util.Date(lastLogin.getTime()));
                }

                user.setRoleName(RoleService.getUserRoleName(resultSet.getLong("id")));
                users.add(user);
            }

            selectStatement.close();
            connection.close();
            return users;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * 根据用户名获取用户信息
     */
    public static User getUserByName(String username) {
        try {
            Class.forName(DATABASE.DRIVER);
            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);
            // 修改点：补充查询新增的字段
            var selectStatement = connection.prepareStatement(
                    "SELECT id, username, password, email, tel, last_login, status FROM user WHERE username = ?"
            );
            selectStatement.setString(1, username);
            var resultSet = selectStatement.executeQuery();
            var user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));

                user.setEmail(resultSet.getString("email"));
                user.setTel(resultSet.getString("tel"));
                user.setStatus(resultSet.getInt("status"));
                Timestamp lastLogin = resultSet.getTimestamp("last_login");
                if (lastLogin != null) {
                    user.setLastLogin(new java.util.Date(lastLogin.getTime()));
                }

                user.setRoleName(RoleService.getUserRoleName(resultSet.getLong("id")));
            }

            selectStatement.close();
            connection.close();
            return user;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据用户ID获取用户信息
     */
    public static User getUserById(Long userId) {
        try {
            Class.forName(DATABASE.DRIVER);
            var connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);
            // 修改点：补充查询新增的字段
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT id, username, password, email, tel, last_login, status FROM user WHERE id = ?"
            );
            selectStatement.setLong(1, userId);
            var resultSet = selectStatement.executeQuery();
            var user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));

                user.setEmail(resultSet.getString("email"));
                user.setTel(resultSet.getString("tel"));
                user.setStatus(resultSet.getInt("status"));
                Timestamp lastLogin = resultSet.getTimestamp("last_login");
                if (lastLogin != null) {
                    user.setLastLogin(new java.util.Date(lastLogin.getTime()));
                }

                user.setRoleName(RoleService.getUserRoleName(resultSet.getLong("id")));
            }

            selectStatement.close();
            connection.close();
            return user;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据用户ID删除用户及用户对应的角色信息
     */
    public static void deleteUserById(Long userId) {
        try {
            Class.forName(DATABASE.DRIVER);
            var connection = DriverManager.getConnection(
                    DATABASE.URL,
                    DATABASE.USERNAME,
                    DATABASE.PASSWORD
            );
            // 删除用户数据
            var deleteUser = connection.prepareStatement(
                    "DELETE FROM `user` WHERE `id` = ?"
            );
            deleteUser.setLong(1, userId);
            deleteUser.execute();
            deleteUser.close();

            // 删除用户对应的角色数据
            var deleteUserRole = connection.prepareStatement(
                    "DELETE FROM user_role WHERE user_id = ?"
            );
            deleteUserRole.setLong(1, userId);
            deleteUserRole.execute();
            deleteUserRole.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加新用户
     * 修改点：接收整个 User 对象作为参数，以便获取所有字段
     */
    public static void addUser(User user) {
        try {
            Class.forName(DATABASE.DRIVER);
            var connection = DriverManager.getConnection(
                    DATABASE.URL,
                    DATABASE.USERNAME,
                    DATABASE.PASSWORD
            );
            // 修改点：SQL 语句增加新字段
            var insertStatement = connection.prepareStatement(
                    "INSERT INTO user (username, password, email, tel, status) VALUES (?, ?, ?, ?, ?)"
            );
            insertStatement.setString(1, user.getUsername());
            insertStatement.setString(2, user.getPassword());
            // 如果前端没有传值，为了防止报空指针，这里做个非空判断保护
            insertStatement.setString(3, user.getEmail() != null ? user.getEmail() : "");
            insertStatement.setString(4, user.getTel() != null ? user.getTel() : "");
            // 状态默认给 1（正常）
            insertStatement.setInt(5, user.getStatus() != null ? user.getStatus() : 1);

            insertStatement.execute();
            insertStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 修改用户信息
     * 注意：这里主要更新邮箱、手机号和状态。不包含密码更新。
     */
    public static void updateUser(User user) {
        try {
            Class.forName(DATABASE.DRIVER);
            var connection = DriverManager.getConnection(
                    DATABASE.URL,
                    DATABASE.USERNAME,
                    DATABASE.PASSWORD
            );

            // 准备 SQL 语句：根据 ID 更新 email, tel, status
            var updateStatement = connection.prepareStatement(
                    "UPDATE user SET email = ?, tel = ?, status = ? WHERE id = ?"
            );

            // 防空指针处理
            updateStatement.setString(1, user.getEmail() != null ? user.getEmail() : "");
            updateStatement.setString(2, user.getTel() != null ? user.getTel() : "");
            updateStatement.setInt(3, user.getStatus() != null ? user.getStatus() : 1);
            updateStatement.setLong(4, user.getId());

            updateStatement.executeUpdate();

            updateStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void updateStatus(Long id, Integer status) {
        try {
            Class.forName(DATABASE.DRIVER);
            var connection = DriverManager.getConnection(
                    DATABASE.URL,
                    DATABASE.USERNAME,
                    DATABASE.PASSWORD
            );
            var updateStatement = connection.prepareStatement(
                    "UPDATE user SET status = ? WHERE id = ?"
            );
            updateStatement.setInt(1, status);
            updateStatement.setLong(2, id);

            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 分页及带条件查询用户列表
     */
    public static Map<String, Object> getPageList(int page, int limit, String searchUsername) {
        Map<String, Object> resultMap = new java.util.HashMap<>();
        try {
            Class.forName(DATABASE.DRIVER);
            Connection connection = DriverManager.getConnection(DATABASE.URL, DATABASE.USERNAME, DATABASE.PASSWORD);

            // 1. 动态拼接 WHERE 条件
            String whereClause = "";
            boolean hasSearch = (searchUsername != null && !searchUsername.trim().isEmpty());
            if (hasSearch) {
                whereClause = " WHERE username LIKE ? ";
            }

            // 2. 查询总记录数 (Total)
            String countSql = "SELECT COUNT(*) FROM user" + whereClause;
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            if (hasSearch) {
                countStatement.setString(1, "%" + searchUsername + "%");
            }
            ResultSet countRs = countStatement.executeQuery();
            long total = 0;
            if (countRs.next()) {
                total = countRs.getLong(1);
            }
            countStatement.close();

            // 3. 分页查询当前页数据 (Records)
            int offset = (page - 1) * limit; // 计算偏移量
            String querySql = "SELECT id, username, password, email, tel, last_login, status FROM user"
                    + whereClause + " LIMIT ?, ?";

            PreparedStatement selectStatement = connection.prepareStatement(querySql);
            int paramIndex = 1;
            if (hasSearch) {
                selectStatement.setString(paramIndex++, "%" + searchUsername + "%");
            }
            selectStatement.setInt(paramIndex++, offset);
            selectStatement.setInt(paramIndex, limit);

            ResultSet resultSet = selectStatement.executeQuery();
            List<User> records = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setTel(resultSet.getString("tel"));
                user.setStatus(resultSet.getInt("status"));

                Timestamp lastLogin = resultSet.getTimestamp("last_login");
                if (lastLogin != null) {
                    user.setLastLogin(new java.util.Date(lastLogin.getTime()));
                }
                user.setRoleName(RoleService.getUserRoleName(resultSet.getLong("id")));
                records.add(user);
            }

            selectStatement.close();
            connection.close();

            resultMap.put("total", total);
            resultMap.put("records", records);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultMap;
    };

    /**
     * 更新用户最后登录时间
     */
    public static void updateLastLogin(Long id, java.util.Date lastLoginDate) {
        try {
            Class.forName(DATABASE.DRIVER);
            var connection = DriverManager.getConnection(
                    DATABASE.URL,
                    DATABASE.USERNAME,
                    DATABASE.PASSWORD
            );
            var updateStatement = connection.prepareStatement(
                    "UPDATE user SET last_login = ? WHERE id = ?"
            );

            // 将 java.util.Date 转换为 java.sql.Timestamp 以匹配数据库的 datetime 类型
            updateStatement.setTimestamp(1, new java.sql.Timestamp(lastLoginDate.getTime()));
            updateStatement.setLong(2, id);

            updateStatement.executeUpdate();

            updateStatement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}