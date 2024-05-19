package br.com.lux.controller.admin;

import br.com.lux.services.car.CarService;
import br.com.lux.services.client.ClientService;
import br.com.lux.services.sales.SalesService;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    private final SalesService salesService;

    @Autowired
    private final CarService carService;

    @Autowired
    private final ClientService clientService;

    @Autowired
    public AdminController(SalesService salesService, CarService carService, ClientService clientService)
    {
        this.salesService = salesService;
        this.carService = carService;
        this.clientService = clientService;
    }

    @RequestMapping
    public String adminHome(Model model, HttpSession session)
    {
        model.addAttribute("user", session.getAttribute("user"));

        model.addAttribute("totalDeCarros", carService.countCars());

        model.addAttribute("totalDeClientes", clientService.countClients());

        model.addAttribute("totalDeVendasPorMes", salesService.monthlyEarnings());

        return "admin/adminHome";
    }
}
