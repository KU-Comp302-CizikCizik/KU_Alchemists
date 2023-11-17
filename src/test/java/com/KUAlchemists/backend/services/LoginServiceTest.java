package com.KUAlchemists.backend.services;
import com.KUAlchemists.backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Properties;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is responsible for testing the LoginService class.
 */
class LoginServiceTest {

    /**
     * The UserRepository that this LoginService uses to validate users.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * The LoginService that this LoginServiceTest tests.
     */

    private LoginService loginService;

    /**
     * This method is called before each test.
     * It initializes the mocks and injects them into the LoginService.
     */

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Properties properties = new Properties();
        // Assuming LoginService needs properties
        loginService = new LoginService(properties);
        loginService.setUserRepository(userRepository);
        // Assuming setter injection
    }

    /**
     * This method tests the validateUser method of the LoginService.
     * It tests the case where the user exists and the password matches.
     */
    @Test
    void validateUserWhenUserExistsAndPasswordMatches() {
        String username = "validUser";
        String password = "correctPassword";
        when(userRepository.getUserPassword(username)).thenReturn(password);

        boolean result = loginService.validateUser(username, password);

        assertTrue(result);
    }

    /**
     * This method tests the validateUser method of the LoginService.
     * It tests the case where the user exists and the password does not match.
     */
    @Test
    void validateUserWhenUserExistsAndPasswordDoesNotMatch() {
        String username = "validUser";
        String password = "correctPassword";
        when(userRepository.getUserPassword(username)).thenReturn("differentPassword");

        boolean result = loginService.validateUser(username, password);

        assertFalse(result);
    }

    /**
     * This method tests the validateUser method of the LoginService.
     * It tests the case where the user does not exist.
     */
    @Test
    void validateUserWhenUserDoesNotExist() {
        String username = "invalidUser";
        when(userRepository.getUserPassword(username)).thenReturn(null);

        boolean result = loginService.validateUser(username, "anyPassword");

        assertFalse(result);
    }
}
