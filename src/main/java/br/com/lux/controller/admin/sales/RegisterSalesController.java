package br.com.lux.controller.admin.sales;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.sales.Sales;
import br.com.lux.domain.user.User;
import br.com.lux.services.sales.SalesService;
import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/register-sales")
public class RegisterSalesController
{
    @Autowired
    private SalesService saleService;

    @GetMapping
    public String registerSales(Model model, HttpSession session)
    {
        model.addAttribute("sale", new Sales());

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "admin/sales/registerSales";
    }

    @PostMapping
    public String registerCarPost(@Valid @ModelAttribute Sales sales, BindingResult bindingResult, HttpSession session, Model model)
    {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        if (bindingResult.hasErrors())
        {
            return "admin/sales/registerSales";
        }

        saleService.registerSale(sales);

        return "redirect:/admin/all-sales";
    }
}
