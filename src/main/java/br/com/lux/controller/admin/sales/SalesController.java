package br.com.lux.controller.admin.sales;


import br.com.lux.services.sales.SalesService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.SequencedCollection;

@Controller
@RequestMapping("/admin/vendas")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping
    public String findSalesByName(Model model)
    {
       SequencedCollection<Object[]> sales = salesService.findSalesByName();
       model.addAttribute("sales", sales);

       return "admin/adminHome";
    }
}
