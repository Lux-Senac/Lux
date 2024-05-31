package br.com.lux.controller.admin.reservation;

import br.com.lux.domain.reservation.Reservation;
import br.com.lux.services.reservation.ReservationService;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/all-reservation")
public class AllReservationController
{
    @Autowired
    private final ReservationService reservationService;

    public AllReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    private final Map<String, Object> response = new HashMap<>();

    @GetMapping
    public String allReservation()
    {
        return "admin/reservation/gridreservation";
    }

    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> allReservationJson(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(value = "search[value]", required = false) String searchTerm
    )
    {
        Page<Reservation> reservations;

        if (searchTerm != null && !searchTerm.isEmpty())
            reservations = reservationService.searchReservations(searchTerm, page, size);
            else
                reservations = reservationService.findReservationAll(page, size);

        response.put("data", reservations.getContent());
        response.put("iTotalRecords", reservations.getTotalElements());
        response.put("iTotalDisplayRecords", reservations.getTotalElements());

        return ResponseEntity.ok(response);
    }
}
