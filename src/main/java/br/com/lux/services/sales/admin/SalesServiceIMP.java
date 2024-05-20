package br.com.lux.services.sales.admin;


import br.com.lux.domain.car.CarPageType;
import br.com.lux.domain.sales.Sales;
import br.com.lux.repository.sales.SalesRepository;
import br.com.lux.services.sales.SalesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;



@Service
public class SalesServiceIMP implements SalesService {

    @Autowired
    private final SalesRepository salesRepository;

    public SalesServiceIMP(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
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

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
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

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Sales> findSaleAll()
    {
        return salesRepository.findAll();
    }

    @Override
    @Transactional
    public void registerSale(Sales sales)
    {
        salesRepository.save(sales);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Sales findSalesById(Integer id)
    {
        return salesRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSales(Integer id)
    {
        salesRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public BigDecimal monthlyEarnings()
    {
        Date data = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

        return salesRepository.ganhosMensais(data);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Map<String, BigDecimal> getMonthlyEarningsForYear()
    {
        int currentYear = LocalDate.now().getYear();

        List<Sales> salesForYear = salesRepository.findByDatavendaBetween(
                Date.from(LocalDate.of(currentYear, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(LocalDate.of(currentYear, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant())
        );

        Map<String, BigDecimal> monthlyEarningsMap = new HashMap<>();
        for (Sales sale : salesForYear) {
            String month = new SimpleDateFormat("MMMM").format(sale.getDatavenda());
            BigDecimal earnings = sale.getPrecovenda();

            monthlyEarningsMap.put(month, monthlyEarningsMap.getOrDefault(month, BigDecimal.ZERO).add(earnings));
        }

        return monthlyEarningsMap;
    }
}
