package br.com.lux.user;

import br.com.lux.controller.admin.user.AllUserController;
import br.com.lux.domain.user.User;
import br.com.lux.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AllUserController.class)
public class AllUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testAllUsers() throws Exception {
        // Arrange
        List<User> users = Arrays.asList(new User(), new User());
        when(userService.findAllUsers()).thenReturn(users);

        // Act & Assert
        mockMvc.perform(get("/admin/all-users"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }
}
