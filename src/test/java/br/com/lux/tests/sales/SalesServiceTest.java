package br.com.lux.tests.sales;

import br.com.lux.domain.car.CarPageType;
import br.com.lux.repository.sales.SalesRepository;
import br.com.lux.services.sales.SalesService;
import br.com.lux.services.sales.admin.SalesServiceIMP;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.SequencedCollection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SalesServiceTest {

    @Mock
    private SalesRepository salesRepository;

    private SalesService salesService;

    @Before
    public void setUp() {
        salesService = new SalesServiceIMP(salesRepository);
    }

    @Test
    public void testFindSalesByNameWithFilter() {
        // Arrange (Preparar)
        String carNameFilter = "Tesla";

        // Criar dados de vendas simulados com alguns nomes de carros contendo "Tesla"
        List<Object[]> mockSalesData = Arrays.asList(
                new Object[]{CarPageType.TeslaModelS, new Date(), BigDecimal.valueOf(100000)},
                new Object[]{CarPageType.TeslaModelX, new Date(), BigDecimal.valueOf(120000)},
                new Object[]{CarPageType.Bmw, new Date(), BigDecimal.valueOf(80000)} // Este não deve ser retornado
        );

        // Simular o comportamento do SalesRepository para retornar os dados simulados
        when(salesRepository.findCarSalesDetails()).thenReturn(mockSalesData);

        // Act (Agir)
        SequencedCollection<Object[]> result = salesService.findSalesByName();

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
}