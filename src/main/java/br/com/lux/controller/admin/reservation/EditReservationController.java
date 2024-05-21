package br.com.lux.controller.admin.reservation;

import br.com.lux.domain.reservation.Reservation;
import br.com.lux.domain.user.User;
import br.com.lux.services.reservation.ReservationService;
import br.com.lux.services.exception.ServiceException;

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
        model.addAttribute("user", session.getAttribute("user"));

        try
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

            return "admin/reservation/updatereservation";
        }
        catch(ServiceException e)
        {
            model.addAttribute("error", e.getMessage());
            return "redirect:/admin/all-reservation";
        }
        catch (Exception e)
        {
            model.addAttribute("error", "Erro inesperado!");
            return "redirect:/admin/all-reservation";
        }
    }

    @PostMapping
    public String editReservationPost(@Valid @ModelAttribute Reservation reservation, Model model,
                                      HttpSession session, BindingResult bindingResult)
    {
        model.addAttribute("user", session.getAttribute("user"));

        if (bindingResult.hasErrors())
        {
            return "admin/reservation/updatereservation";
        }
        else
        {
            try
            {
                reservationService.registerReservation(reservation);

                return "redirect:/admin/all-reservation";
            }
            catch (ServiceException e)
            {
                model.addAttribute("error", e.getMessage());
                return "admin/reservation/updatereservation";
            }
            catch (Exception e)
            {
                model.addAttribute("error", "Erro inesperado!");
                return "admin/reservation/updatereservation";
            }
        }
    }
}
