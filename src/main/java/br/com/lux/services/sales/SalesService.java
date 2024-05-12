package br.com.lux.services.sales;

import br.com.lux.domain.sales.Sales;

import java.util.List;
import java.util.SequencedCollection;

public interface SalesService
{
    List<Sales> findSaleAll();

    SequencedCollection<Object[]> findSalesByName(String carNameFilter);

    List<Object[]> findTotalSalesPerCarModel();

    void registerSale(Sales sales);

    Sales findSalesById(Integer id);
}
