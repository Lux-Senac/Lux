package br.com.lux.controller.admin.car;

import br.com.lux.controller.car.CarController;
import br.com.lux.domain.car.Car;
import br.com.lux.domain.user.User;
import br.com.lux.services.car.CarService;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/all-cars")
public class AllCarController
{
    @Autowired
    private CarService carService;

    @GetMapping
    public String allCars(Model model, HttpSession session)
    {
        List<Car> cars = carService.findCarAll();
        model.addAttribute("cars", cars);

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "admin/car/gridcar";
    }
}
