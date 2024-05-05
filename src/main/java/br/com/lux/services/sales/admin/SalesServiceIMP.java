package br.com.lux.services.sales.admin;


import br.com.lux.repository.sales.SalesRepository;
import br.com.lux.services.sales.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Object[]> findTotalSalesPerCarModel() {
        return salesRepository.findTotalSalesPerCarModel();
    }
}
