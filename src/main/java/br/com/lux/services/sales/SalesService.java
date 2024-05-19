package br.com.lux.services.sales;

import br.com.lux.domain.sales.Sales;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SequencedCollection;

public interface SalesService
{
    List<Sales> findSaleAll();

    SequencedCollection<Object[]> findSalesByName(String carNameFilter);

    List<Object[]> findTotalSalesPerCarModel();

    void registerSale(Sales sales);

    Sales findSalesById(Integer id);

    void deleteSales(Integer id);

    BigDecimal monthlyEarnings();

    Map<String, BigDecimal> getMonthlyEarningsForYear();
}
