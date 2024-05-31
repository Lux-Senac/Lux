package br.com.lux.user;

import br.com.lux.domain.user.User;
import br.com.lux.repository.user.UserRepository;
import br.com.lux.services.email.EmailService;
import br.com.lux.services.gravatar.GravatarService;
import br.com.lux.services.user.login.LoginService;

import br.com.lux.services.user.login.PasswordUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest
{
    @Mock
    private UserRepository userRepository;

    @Mock
    private GravatarService gravatarService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private LoginService loginService;

    @Before
    public void setUp() {
        loginService = new LoginService(userRepository, gravatarService, emailService);
    }

    @Test
    public void testAuthenticateWithValidCredentials() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        User user = new User();
        user.setEmail(email);
        user.setPassword(PasswordUtils.encryptPassword(password));

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = loginService.authenticate(email, password);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void testAuthenticateWithInvalidEmail() {
        // Arrange
        String email = "invalid@example.com";
        String password = "password";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = loginService.authenticate(email, PasswordUtils.encryptPassword(password));

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    public void testAuthenticateWithInvalidPassword() {
        // Arrange
        String email = "test@example.com";
        String password = "wrongpassword";
        String correctPassword = "password";

        User user = new User();
        user.setEmail(email);
        user.setPassword(PasswordUtils.encryptPassword(correctPassword)); // Define a senha criptografada

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = loginService.authenticate(email, password);

        // Assert
        assertFalse(result.isPresent()); // A autenticação deve falhar
    }
}
