package br.com.lux.sales;

import br.com.lux.controller.admin.sales.DeleteSalesController;
import br.com.lux.services.sales.SalesService;
import br.com.lux.services.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class DeleteSalesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SalesService salesService;

    @InjectMocks
    private DeleteSalesController deleteSalesController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(deleteSalesController).build();
    }

    @Test
    public void deleteSales_WhenSuccessful_RedirectsToAllSales() throws Exception {
        // Arrange
        Integer saleId = 1;
        doNothing().when(salesService).deleteSales(saleId);

        // Act & Assert
        mockMvc.perform(delete("/admin/delete-sales")
                        .param("id", saleId.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/all-sales"));

        verify(salesService, times(1)).deleteSales(saleId);
    }

    @Test
    public void deleteSales_WhenServiceException_RedirectsWithError() throws Exception {
        // Arrange
        Integer saleId = 1;
        doThrow(new ServiceException("Erro ao deletar a venda")).when(salesService).deleteSales(saleId);

        // Act & Assert
        mockMvc.perform(delete("/admin/delete-sales")
                        .param("id", saleId.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/admin/all-sales*"));

        verify(salesService, times(1)).deleteSales(saleId);
    }
}