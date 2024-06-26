package br.com.lux.services.sales.admin;

import br.com.lux.domain.car.CarPageType;
import br.com.lux.domain.sales.Sales;
import br.com.lux.domain.user.User;
import br.com.lux.repository.sales.SalesRepository;
import br.com.lux.repository.user.UserRepository;
import br.com.lux.services.exception.ServiceException;
import br.com.lux.services.sales.SalesService;

import br.com.lux.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private final UserRepository userRepository;

    public SalesServiceIMP(SalesRepository salesRepository, UserRepository userRepository)
    {
        this.salesRepository = salesRepository;
        this.userRepository = userRepository;
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
    public Page<Sales> searchSales(String searchTerm, int page, int size)
    {
        try
        {
            return salesRepository.findByUserNameAndClientNameAndCarName(searchTerm, searchTerm, searchTerm, PageRequest.of(page, size));
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar vendas por termo de pesquisa! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Sales> findSaleAll(int page, int size)
    {
        try
        {
            return salesRepository.findAll(PageRequest.of(page, size));
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar todas as vendas paginadas! " + e.getMessage());
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

            if(sales.getCarro().getId() == null)
                throw new ServiceException("Selecionar um carro é obrigatório!");

            if(sales.getCliente().getId() == null)
                throw new ServiceException("Selecionar um cliente é obrigatório!");

            if(sales.getUsuario().getId() == null)
                throw new ServiceException("Selecionar um usuário é obrigatório!");

            User user = userRepository.findById(sales.getUsuario().getId()).orElse(null);
            if(user == null)
                throw new ServiceException("Usuário inválido!");

            if(user.getTipo().name() != "ADMIN")
                throw new ServiceException("Apenas usuários administradores podem registrar vendas!");

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
