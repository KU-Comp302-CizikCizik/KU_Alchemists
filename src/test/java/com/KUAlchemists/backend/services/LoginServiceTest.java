package com.KUAlchemists.backend.services;
import com.KUAlchemists.backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Properties;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    @Mock
    private UserRepository userRepository;

    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Properties properties = new Properties(); // Assuming LoginService needs properties
        loginService = new LoginService(properties);
        loginService.setUserRepository(userRepository); // Assuming setter injection
    }

    @Test
    void validateUserWhenUserExistsAndPasswordMatches() {
        String username = "validUser";
        String password = "correctPassword";
        when(userRepository.getUserPassword(username)).thenReturn(password);

        boolean result = loginService.validateUser(username, password);

        assertTrue(result);
    }

    @Test
    void validateUserWhenUserExistsAndPasswordDoesNotMatch() {
        String username = "validUser";
        String password = "correctPassword";
        when(userRepository.getUserPassword(username)).thenReturn("differentPassword");

        boolean result = loginService.validateUser(username, password);

        assertFalse(result);
    }

    @Test
    void validateUserWhenUserDoesNotExist() {
        String username = "invalidUser";
        when(userRepository.getUserPassword(username)).thenReturn(null);

        boolean result = loginService.validateUser(username, "anyPassword");

        assertFalse(result);
    }
}
