package br.com.lux.services.sales.admin;


import br.com.lux.domain.car.CarPageType;
import br.com.lux.repository.sales.SalesRepository;
import br.com.lux.services.sales.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SequencedCollection;


@Service
public class SalesServiceIMP implements SalesService {

    @Autowired
    private SalesRepository salesRepository;

    public SalesServiceIMP(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public SequencedCollection<Object[]> findSalesByName() {
        return salesRepository.findCarSalesDetails();
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
}
