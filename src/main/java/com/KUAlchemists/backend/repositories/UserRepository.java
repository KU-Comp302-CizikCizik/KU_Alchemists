package com.KUAlchemists.backend.repositories;

import java.sql.*;
import java.util.Properties;

/**
 * This class is responsible for all database operations related to users.
 */
public class UserRepository {

    /**
     * The properties that this repository needs to connect to the database.
     */
    private Properties properties;

    /**
     * Constructor for UserRepository.
     * @param properties The properties that this repository needs to connect to the database.
     */
    public UserRepository(Properties properties) {
        this.properties = properties;
    }

    /**
     * This method returns a connection to the database.
     * @return A connection to the database.
     * @throws SQLException If the connection could not be established.
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("database.url"),
                properties.getProperty("database.user"),
                properties.getProperty("database.password")
        );
    }

    /**
     * This method returns the hashed password of a user.
     * @param username The username of the user.
     * @return The hashed password of the user.
     */
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
