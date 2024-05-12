package br.com.lux.controller.admin.reservation;

import br.com.lux.services.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/create-reservation")
public class RegisterReservationController
{
    @Autowired
    private final ReservationService reservationService;

    public RegisterReservationController(ReservationService reservationService)
    {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String createReservation()
    {
        return "admin/reservation/registerrervation";
    }

    @PostMapping
    public String createReservationPost()
    {
        return "redirect:/admin/all-reservation";
    }
}
