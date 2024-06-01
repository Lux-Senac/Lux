package br.com.lux.sales;

import br.com.lux.controller.admin.sales.AllSalesController;
import br.com.lux.domain.sales.Sales;
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
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AllSalesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SalesService salesService;

    @Mock
    private Model model;

    @InjectMocks
    private AllSalesController allSalesController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(allSalesController).build();
    }

    @Test
    public void allSales_ShouldAddSalesToModel_AndReturnViewName() throws Exception {
        // Given
        List<Sales> salesList = Arrays.asList(new Sales(), new Sales());
        given(salesService.findSaleAll()).willReturn(salesList);

        // When & Then
        mockMvc.perform(get("/admin/all-sales"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/sales/gridSales"))
                .andExpect(model().attribute("sales", salesList));
    }

    @Test
    public void allSales_WhenServiceException_ShouldAddErrorMessageToModel_AndReturnViewName() throws Exception {
        // Given
        String errorMessage = "Erro de serviço";
        given(salesService.findSaleAll()).willThrow(new ServiceException(errorMessage));

        // When & Then
        mockMvc.perform(get("/admin/all-sales"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/sales/gridSales"))
                .andExpect(model().attribute("error", errorMessage));
    }

    // Additional tests can be added here to cover more scenarios
}
