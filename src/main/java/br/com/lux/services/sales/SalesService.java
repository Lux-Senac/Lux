package br.com.lux.services.sales;

import br.com.lux.domain.sales.Sales;

import java.util.SequencedCollection;

public interface SalesAdminService
{
    SequencedCollection<Object[]> findSalesByName();
}
