package br.com.lux.user;

import br.com.lux.domain.user.User;
import br.com.lux.domain.user.UserType;
import br.com.lux.repository.user.UserRepository;
import br.com.lux.services.email.EmailService;
import br.com.lux.services.gravatar.GravatarService;
import br.com.lux.services.user.UserService;
import br.com.lux.services.user.login.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private GravatarService gravatarService;

    @Mock
    private EmailService emailService;

    private UserService userService;

    @Before
    public void setUp() {
        userService = new LoginService(userRepository, gravatarService, emailService);
    }

    @Test
    public void testCreateUserWithValidUser() {
        // Arrange (Preparar)
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("password");

        // Simula o comportamento do UserRepository para retornar Optional.empty() quando findByEmail é chamado
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        // Simula o comportamento do GravatarService para retornar um URL de exemplo
        when(gravatarService.getGravatarUrl(user.getEmail().toLowerCase())).thenReturn("https://www.gravatar.com/avatar/123");

        // Act (Agir)
        Optional<User> result = userService.createUser(user);

        // Assert (Verificar)
        // Verifica se o resultado é um Optional presente (ou seja, o usuário foi criado)
        assertTrue(result.isPresent());

        // Obtém o usuário criado a partir do Optional
        User createdUser = result.get();

        // Verifica se as propriedades do usuário criado correspondem aos valores esperados
        assertEquals("testuser", createdUser.getUsername());
        assertEquals("test@example.com", createdUser.getEmail());
        assertEquals("https://www.gravatar.com/avatar/123", createdUser.getUrlavatar());
        assertEquals(UserType.CLIENTE, createdUser.getTipo());

        // Verifica se o método save do UserRepository foi chamado com o usuário correto
        verify(userRepository).save(user);
    }
}
