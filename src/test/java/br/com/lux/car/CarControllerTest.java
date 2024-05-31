/*
package br.com.lux.car;

import br.com.lux.controller.car.CarController;
import br.com.lux.services.car.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    public void testFindAllCarsThrowsException() throws Exception {
        // Arrange
        when(carService.findCarAll(1, 10)).thenThrow(new RuntimeException("Erro ao buscar todos os carros!"));

        // Act & Assert
        mockMvc.perform(get("/carros/find-all"))
                .andExpect(status().isInternalServerError());
    }
}
 */