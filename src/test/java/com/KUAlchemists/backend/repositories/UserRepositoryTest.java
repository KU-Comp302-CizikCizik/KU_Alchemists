package com.KUAlchemists.backend.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.Properties;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    private UserRepository userRepository;
    private MockedStatic<DriverManager> mockedDriverManager;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        Properties properties = new Properties(); // Mock properties if needed
        // mock properties

        properties.setProperty("database.url", "jjdbc:mysql://localhost:3306");
        properties.setProperty("database.user", "root");
        properties.setProperty("database.password", "");

        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("password")).thenReturn("hashed_password");

        mockedDriverManager = mockStatic(DriverManager.class);
        mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(connection);

        userRepository = new UserRepository(properties);
    }

    @Test
    void getUserPassword() throws Exception {
        String password = userRepository.getUserPassword("username");
        assertEquals("hashed_password", password);
    }

    @AfterEach
    void tearDown() {
        mockedDriverManager.close();
    }
}
