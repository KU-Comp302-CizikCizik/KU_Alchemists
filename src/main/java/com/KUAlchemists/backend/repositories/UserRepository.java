package com.KUAlchemists.backend.repositories;

import java.sql.*;
import java.util.Properties;

public class UserRepository {

    private Properties properties;

    public UserRepository(Properties properties) {
        this.properties = properties;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("database.url"),
                properties.getProperty("database.user"),
                properties.getProperty("database.password")
        );
    }

    public String getUserPassword(String username) {
        String password = null;
        String sql = "SELECT password FROM users WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                password = rs.getString("password");
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return password;
    }
}
// Other database operations
