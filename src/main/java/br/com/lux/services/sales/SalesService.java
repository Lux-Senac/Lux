package br.com.lux.services.sales;

import br.com.lux.domain.sales.Sales;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SequencedCollection;

@Service
public interface SalesService
{

    SequencedCollection<Object[]> findSalesByName(String carNameFilter);

    void registerSale(Sales sales);

    Sales findSalesById(Integer id);

    void deleteSales(Integer id);

    BigDecimal monthlyEarnings();

    Map<String, BigDecimal> getMonthlyEarningsForYear();

    Page<Sales> searchSales(String searchTerm, int page, int size);

    Page<Sales> findSaleAll(int page, int size);
}
