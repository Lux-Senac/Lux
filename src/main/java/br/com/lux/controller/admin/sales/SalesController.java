package br.com.lux.controller.admin.sales;


import br.com.lux.domain.sales.Sales;
import br.com.lux.repository.sales.SalesRepository;
import br.com.lux.services.sales.SalesAdminService;
import br.com.lux.services.sales.SalesAdminServiceIMP;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.SequencedCollection;

@Controller
@RequestMapping("/admin/vendas")
public class SalesController {

    @Autowired
    private SalesAdminService salesAdminService;

    @GetMapping
    public String findSalesByName(Model model) {
       SequencedCollection<Object[]> sales = salesAdminService.findSalesByName();
            model.addAttribute("sales", sales);
        return "admin/adminHome";
    }
}
