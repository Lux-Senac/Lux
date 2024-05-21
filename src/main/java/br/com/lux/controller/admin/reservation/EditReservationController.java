package br.com.lux.controller.admin.reservation;

import br.com.lux.domain.reservation.Reservation;
import br.com.lux.domain.user.User;
import br.com.lux.services.reservation.ReservationService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/edit-reservation")
public class EditReservationController
{
    @Autowired
    private final ReservationService reservationService;

    public EditReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String editReservation(@RequestParam("id") Integer id, Model model, HttpSession session)
    {
        if(id == null)
        {
            return "redirect:/admin/all-reservation";
        }

        Reservation reservation = reservationService.findReservationById(id);

        if(reservation == null)
        {
            return "redirect:/admin/all-reservation";
        }

        model.addAttribute("reservation", reservation);
        model.addAttribute("user", session.getAttribute("user"));

        return "admin/reservation/updatereservation";
    }

    @PostMapping
    public String editReservationPost(@Valid @ModelAttribute Reservation reservation, Model model,
                                      HttpSession session, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "admin/reservation/updatereservation";
        }

        reservationService.registerReservation(reservation);

        return "redirect:/admin/all-reservation";
    }
}
