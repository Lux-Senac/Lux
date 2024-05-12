package br.com.lux.controller.admin.sales;

import br.com.lux.domain.sales.Sales;
import br.com.lux.domain.user.User;
import br.com.lux.services.sales.SalesService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/all-sales")
public class AllSalesController
{
    @Autowired
    private final SalesService saleService;

    public AllSalesController(SalesService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public String allSales(Model model, HttpSession session)
    {
        List<Sales> sales = saleService.findSaleAll();
        model.addAttribute("sales", sales);

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "admin/sales/gridSales";
    }
}
