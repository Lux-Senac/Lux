package br.com.lux.services.sales.admin;


import br.com.lux.domain.car.CarPageType;
import br.com.lux.domain.sales.Sales;
import br.com.lux.repository.sales.SalesRepository;
import br.com.lux.services.sales.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SalesServiceIMP implements SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public SalesServiceIMP(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public SequencedCollection<Object[]> findSalesByName(String carNameFilter) {
        List<Object[]> salesData = salesRepository.findCarSalesDetails();
        List<Object[]> filteredSales = new ArrayList<>();
        for (Object[] sale : salesData) {
            CarPageType carPageType = (CarPageType) sale[0];
            if (carPageType.name().contains(carNameFilter)) {
                filteredSales.add(sale);
            }
        }
        return filteredSales;
    }

    public List<Object[]> findTotalSalesPerCarModel()
    {
        List<Object[]> salesData = salesRepository.findTotalSalesPerCarModel();

        Map<String, Integer> salesMap = new HashMap<>();
        for (Object[] row : salesData) {
            CarPageType carPageType = (CarPageType) row[0];
            salesMap.put(carPageType.name(), ((Long) row[1]).intValue());
        }

        return salesData;
    }

    public List<Sales> findSaleAll()
    {
        return salesRepository.findAll();
    }

    public void registerSale(Sales sales)
    {
        salesRepository.save(sales);
    }

    public Sales findSalesById(Integer id)
    {
        return salesRepository.findById(id).orElse(null);
    }

    public void deleteSales(Integer id)
    {
        salesRepository.deleteById(id);
    }
}
