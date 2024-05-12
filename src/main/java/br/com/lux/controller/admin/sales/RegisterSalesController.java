package br.com.lux.controller.admin.sales;

import br.com.lux.domain.sales.Sales;
import br.com.lux.domain.user.User;
import br.com.lux.services.car.CarService;
import br.com.lux.services.client.ClientService;
import br.com.lux.services.sales.SalesService;
import br.com.lux.services.user.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String registerSales(Model model, HttpSession session)
    {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        model.addAttribute("sales", new Sales());

        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("cars", carService.findCarAll());
        model.addAttribute("clients", clientService.findAllClients());

        return "admin/sales/registersales";
    }

    @PostMapping
    public String registerCarPost(@Valid @ModelAttribute Sales sales, BindingResult bindingResult, HttpSession session, Model model)
    {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("cars", carService.findCarAll());
        model.addAttribute("clients", clientService.findAllClients());

        if (bindingResult.hasErrors())
        {
            return "admin/sales/registersales";
        }

        saleService.registerSale(sales);

        return "redirect:/admin/all-sales";
    }
}