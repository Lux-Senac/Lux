package br.com.lux.controller.admin.sales;


import br.com.lux.services.sales.SalesService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.SequencedCollection;

@Controller
@RequestMapping("/admin/vendas")
public class SalesController {

    @Autowired
    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping
    public String findSalesByName(@PathVariable String carNameFilter, Model model)
    {
       SequencedCollection<Object[]> sales = salesService.findSalesByName(carNameFilter);
       model.addAttribute("sales", sales);

       return "admin/adminHome";
    }
}
