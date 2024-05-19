package br.com.lux.controller.admin.reservation;

import br.com.lux.domain.reservation.Reservation;
import br.com.lux.services.car.CarService;
import br.com.lux.services.client.ClientService;
import br.com.lux.services.reservation.ReservationService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/create-reservation")
public class RegisterReservationController
{
    @Autowired
    private final ReservationService reservationService;

    @Autowired
    private final CarService carService;

    @Autowired
    private final ClientService clientService;

    public RegisterReservationController(ReservationService reservationService, CarService carService, ClientService clientService) {
        this.reservationService = reservationService;
        this.carService = carService;
        this.clientService = clientService;
    }

    @GetMapping
    public String createReservation(Model model, HttpSession session)
    {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("cars", carService.findCarAll());
        model.addAttribute("clients", clientService.findAllClients());

        return "admin/reservation/registerrervation";
    }

    @PostMapping
    public String createReservationPost(@Valid @ModelAttribute Reservation reservation, HttpSession session,
                                        Model model, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("cars", carService.findCarAll());
            model.addAttribute("clients", clientService.findAllClients());

            return "admin/reservation/registerrervation";
        }

        reservationService.registerReservation(reservation);

        return "redirect:/admin/all-reservation";
    }

}
