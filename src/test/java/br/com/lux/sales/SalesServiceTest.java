package br.com.lux.sales;

import br.com.lux.domain.car.CarPageType;
import br.com.lux.repository.sales.SalesRepository;
import br.com.lux.repository.user.UserRepository;
import br.com.lux.services.sales.SalesService;
import br.com.lux.services.sales.admin.SalesServiceIMP;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SalesServiceTest {

    @Mock
    private SalesRepository salesRepository;

    @Mock
    private UserRepository userRepository;

    private SalesService salesService;

    @Before
    public void setUp() {
        salesService = new SalesServiceIMP(salesRepository, userRepository);
    }

    @Test
    public void testFindSalesByNameWithFilter() {
        // Arrange (Preparar)
        String carNameFilter = "Tesla";

        // Criar dados de vendas simulados com alguns nomes de carros contendo "Tesla"
        List<Object[]> mockSalesData = Arrays.asList(
                new Object[]{CarPageType.TeslaModelS, new Date(), BigDecimal.valueOf(100000)},
                new Object[]{CarPageType.TeslaModelX, new Date(), BigDecimal.valueOf(120000)}
        );

        // Simular o comportamento do SalesRepository para retornar os dados simulados
        when(salesRepository.findCarSalesDetails()).thenReturn(mockSalesData);

        // Act (Agir)
        SequencedCollection<Object[]> result = salesService.findSalesByName(carNameFilter);

        // Assert (Verificar)
        // Verificar se o tamanho da lista de resultados está correto (deve ser 2, pois há 2 carros "Tesla")
        assertEquals(2, result.size());

        // Iterar sobre os resultados e verificar se cada nome de carro contém "Tesla"
        for (Object[] sale : result) {
            CarPageType carPageType = (CarPageType) sale[0];
            String saleCarName = carPageType.name();
            assertTrue(saleCarName.contains(carNameFilter));
        }
    }

    @Test
    public void testFindSalesByNameWithDifferentFilter() {
        // Arrange
        String carNameFilter = "Bmw";

        // Criar dados de vendas simulados com alguns carros "Bmw"
        List<Object[]> mockSalesData = Arrays.asList(
                new Object[]{CarPageType.Bmw, new Date(), BigDecimal.valueOf(80000)},
                new Object[]{CarPageType.TeslaModelS, new Date(), BigDecimal.valueOf(100000)}, // Este não deve ser retornado
                new Object[]{CarPageType.Bmw, new Date(), BigDecimal.valueOf(90000)}
        );

        when(salesRepository.findCarSalesDetails()).thenReturn(mockSalesData);

        // Act
        SequencedCollection<Object[]> result = salesService.findSalesByName(carNameFilter);

        // Assert
        assertEquals(2, result.size());
        for (Object[] sale : result) {
            CarPageType carPageType = (CarPageType) sale[0];
            String saleCarName = carPageType.name();
            assertTrue(saleCarName.contains(carNameFilter));
        }
    }

    @Test
    public void testFindSalesByNameWithEmptyList() {
        // Arrange
        List<Object[]> mockSalesData = Collections.emptyList();
        when(salesRepository.findCarSalesDetails()).thenReturn(mockSalesData);

        // Act
        SequencedCollection<Object[]> result = salesService.findSalesByName("Tesla");

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindSalesByNameWithVariousData() {
        // Arrange
        String carNameFilter = "Porsche";

        // Criar dados de vendas simulados com diferentes modelos, datas e preços
        List<Object[]> mockSalesData = Arrays.asList(
                new Object[]{CarPageType.Porsche, new Date(2023, 1, 15), BigDecimal.valueOf(150000)},
                new Object[]{CarPageType.Porsche, new Date(2023, 2, 20), BigDecimal.valueOf(160000)},
                new Object[]{CarPageType.Bmw, new Date(2023, 3, 5), BigDecimal.valueOf(85000)} // Este não deve ser retornado
        );

        when(salesRepository.findCarSalesDetails()).thenReturn(mockSalesData);

        // Act
        SequencedCollection<Object[]> result = salesService.findSalesByName(carNameFilter);

        // Assert
        assertEquals(2, result.size());
        for (Object[] sale : result) {
            CarPageType carPageType = (CarPageType) sale[0];
            String saleCarName = carPageType.name();
            assertTrue(saleCarName.contains(carNameFilter));
        }
    }
}