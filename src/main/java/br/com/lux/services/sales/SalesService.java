package br.com.lux.services.sales;

import java.util.List;
import java.util.SequencedCollection;

public interface SalesService
{
    SequencedCollection<Object[]> findSalesByName(String carNameFilter);

    List<Object[]> findTotalSalesPerCarModel();
}
