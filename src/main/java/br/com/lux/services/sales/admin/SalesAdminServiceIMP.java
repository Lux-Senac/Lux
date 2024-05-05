package br.com.lux.services.sales.admin;


import br.com.lux.repository.sales.SalesRepository;
import br.com.lux.services.sales.SalesAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.SequencedCollection;


@Service
public class SalesAdminServiceIMP implements SalesAdminService {

    @Autowired
    private SalesRepository salesRepository;

    public SalesAdminServiceIMP(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public SequencedCollection<Object[]> findSalesByName() {
        return salesRepository.findCarSalesDetails();
    }

}
