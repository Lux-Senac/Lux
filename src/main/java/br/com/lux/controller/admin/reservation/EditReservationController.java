package br.com.lux.controller.admin.reservation;

import br.com.lux.domain.user.User;
import br.com.lux.services.reservation.ReservationService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);



        return "admin/reservation/registerrervation";
    }

    @PostMapping
    public String editReservationPost()
    {


        return "redirect:/admin/all-reservation";
    }
}
