package br.com.lux.services.sales.admin;


import br.com.lux.domain.car.CarPageType;
import br.com.lux.domain.sales.Sales;
import br.com.lux.repository.sales.SalesRepository;
import br.com.lux.services.exception.ServiceException;
import br.com.lux.services.sales.SalesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.dao.DataIntegrityViolationException;
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
    public SequencedCollection<Object[]> findSalesByName(String carNameFilter)
    {
        try
        {
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
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar vendas por nome! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Object[]> findTotalSalesPerCarModel()
    {
        try
        {
            List<Object[]> salesData = salesRepository.findTotalSalesPerCarModel();

            Map<String, Integer> salesMap = new HashMap<>();
            for (Object[] row : salesData) {
                CarPageType carPageType = (CarPageType) row[0];
                salesMap.put(carPageType.name(), ((Long) row[1]).intValue());
            }

            return salesData;
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar total de vendas por modelo de carro! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Sales> findSaleAll()
    {
        try
        {
            return salesRepository.findAll();
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar todas as vendas! " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void registerSale(Sales sales)
    {
        try
        {
            if(sales == null)
                throw new ServiceException("Venda inválida!");

            if(sales.getCarro() == null)
                throw new ServiceException("Carro inválido!");

            if(sales.getCliente() == null)
                throw new ServiceException("Cliente inválido!");

            if(sales.getUsuario() == null)
                throw new ServiceException("Usuário inválido!");


            salesRepository.save(sales);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new ServiceException("Erro ao registrar venda! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Sales findSalesById(Integer id)
    {
        try
        {
            return salesRepository.findById(id).orElse(null);
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar venda por id! " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteSales(Integer id)
    {
        try
        {
            if (id == null)
                throw new ServiceException("Id inválido!");

            salesRepository.deleteById(id);
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao deletar venda por id! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public BigDecimal monthlyEarnings()
    {
        try
        {
            Date data = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

            return salesRepository.ganhosMensais(data);
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar ganhos mensais! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Map<String, BigDecimal> getMonthlyEarningsForYear()
    {
        try
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
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar ganhos mensais por ano! " + e.getMessage());
        }
    }
}
