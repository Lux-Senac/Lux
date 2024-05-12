package br.com.lux.controller.admin.sales;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.sales.Sales;
import br.com.lux.domain.user.User;
import br.com.lux.services.car.CarService;
import br.com.lux.services.client.ClientService;
import br.com.lux.services.sales.SalesService;
import br.com.lux.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/edit-sales")
public class EditSalesController
{
    @Autowired
    private final SalesService salesService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final ClientService clientService;

    public EditSalesController(SalesService salesService, UserService userService, ClientService clientService) {
        this.salesService = salesService;
        this.userService = userService;
        this.clientService = clientService;
    }

    @GetMapping
    public String editSales(@RequestParam("id") Integer id, Model model, HttpSession session)
    {
        if(id == null)
        {
            return "redirect:/admin/all-sales";
        }

        Sales sales = salesService.findSalesById(id);

        if(sales == null)
        {
            return "redirect:/admin/all-sales";
        }

        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("sales", sales);
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("clients", clientService.findAllClients());

        return "admin/sales/updatesales";
    }

    @PostMapping
    public String editSalesPost(@Valid @ModelAttribute Sales sales, Model model, HttpSession session, BindingResult bindingResult)
    {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("clients", clientService.findAllClients());

        if(bindingResult.hasErrors())
        {
            return "admin/sales/updatesales";
        }

        salesService.registerSale(sales);

        return "redirect:/admin/all-sales";
    }
}
