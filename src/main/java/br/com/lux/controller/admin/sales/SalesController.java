package br.com.lux.controller.admin.sales;


import br.com.lux.services.sales.SalesService;

import org.hibernate.service.spi.ServiceException;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SequencedCollection;

@Controller
@RequestMapping("/admin/vendas")
public class SalesController {

    @Autowired
    private final SalesService salesService;

    public SalesController(SalesService salesService)
    {
        this.salesService = salesService;
    }


    @GetMapping("/monthlyEarnings")
    @ResponseBody
    public Map<String, BigDecimal> monthlyEarnings()
    {
        try
        {
            return salesService.getMonthlyEarningsForYear();
        }
        catch(ServiceException e)
        {
            throw new ServiceException(e.getMessage());
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao buscar vendas!");
        }
    }
}
