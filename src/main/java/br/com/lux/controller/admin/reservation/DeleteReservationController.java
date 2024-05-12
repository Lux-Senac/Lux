package br.com.lux.controller.admin.reservation;

import br.com.lux.services.reservation.ReservationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/delete-reservation")
public class DeleteReservationController
{
    @Autowired
    private final ReservationService reservationService;

    public DeleteReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @DeleteMapping
    public String deleteReservation(@RequestParam("id") Integer id)
    {
        if(id == null)
        {
            return "redirect:/admin/all-reservation";
        }

        reservationService.deleteReservation(id);

        return "redirect:admin/all-reservation";
    }
}
