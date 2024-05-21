package br.com.lux.controller.admin;

import br.com.lux.domain.reservation.ReservationStatus;
import br.com.lux.services.car.CarService;
import br.com.lux.services.client.ClientService;
import br.com.lux.services.reservation.ReservationService;
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
    private final ReservationService reservationService;

    @Autowired
    public AdminController(SalesService salesService, CarService carService, ClientService clientService, ReservationService reservationService)
    {
        this.salesService = salesService;
        this.carService = carService;
        this.clientService = clientService;
        this.reservationService = reservationService;
    }

    @RequestMapping
    public String adminHome(Model model, HttpSession session)
    {
        model.addAttribute("totalDeCarros", carService.countCars());

        model.addAttribute("totalDeClientes", clientService.countClients());

        model.addAttribute("totalDeVendasPorMes", salesService.monthlyEarnings());

        model.addAttribute("totalDeReservas", reservationService.countByStatusreserva(ReservationStatus.ESPERA));

        return "admin/adminHome";
    }
}
