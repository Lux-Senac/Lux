package br.com.lux.controller.admin.reservation;

import br.com.lux.domain.user.User;
import br.com.lux.services.reservation.ReservationService;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/all-reservation")
public class AllReservationController
{
    @Autowired
    private final ReservationService reservationService;

    public AllReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String allReservation(Model model, HttpSession session)
    {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        model.addAttribute("reservations", reservationService.findAllReservations());

        return "admin/reservation/gridreservation";
    }
}
