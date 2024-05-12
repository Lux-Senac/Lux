package br.com.lux.controller.testdrivereserve;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.client.Client;
import br.com.lux.domain.reservation.ReservationStatus;
import br.com.lux.domain.reservation.ReservationType;
import br.com.lux.domain.user.User;
import br.com.lux.services.car.CarService;
import br.com.lux.services.client.ClientService;
import br.com.lux.services.reservation.ReservationService;
import br.com.lux.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestDriveReserveController
{
    private final CarService carService;

    private final ClientService clientService;

    private final UserService userService;

    private final ReservationService reservationService;

    @Autowired
    public TestDriveReserveController(CarService carService, ClientService clientService, UserService userService, ReservationService reservationService)
    {
        this.carService = carService;
        this.clientService = clientService;
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @GetMapping("/agendar-test-drive/{id}")
    public String agendarTestDrive(@PathVariable Integer id, Model model, HttpSession session)
    {
        Car car = carService.findCarById(id);

        if (car == null)
            return "redirect:/home";

        User user = (User) session.getAttribute("user");

        if (user != null && user.getCliente() != null)
        {
            Client client = clientService.findClientById(user.getCliente().getId());

            if (client != null)
                model.addAttribute("client", client);
        }
        else
            model.addAttribute("client", new Client());

        model.addAttribute("car", car);

        return "testdrivereserve/agendetestdrive";
    }

    @PostMapping("/agendar-test-drive/{id}")
    public String agendarTestDrive(@PathVariable Integer id, @Valid @ModelAttribute Client client,
                                   BindingResult bindingResult, Model model, HttpSession session)
    {
        Car car = carService.findCarById(id);

        if (car == null)
            return "redirect:/home";

        model.addAttribute("car", car);

        if (bindingResult.hasErrors())
            return "testdrivereserve/agendetestdrive";

        User user = (User) session.getAttribute("user");

        if (user != null && user.getCliente() == null)
        {
            client = clientService.registerClient(client);
            userService.changeClientId(user.getId(), client);
        }
        else
            client = clientService.registerClient(client);

        reservationService.registerReservation(client, car, ReservationType.TEST_DRIVE);

        return "testdrivereserve/agendetestdrive" ;
    }

    @GetMapping("/reservar-carro")
    public String reservarCarro(@RequestParam("id") Integer id,Model model)
    {
        return "testdrivereserve/reserve";
    }
}
