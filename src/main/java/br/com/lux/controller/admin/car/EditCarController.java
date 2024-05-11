package br.com.lux.controller.admin.car;

import br.com.lux.controller.car.CarController;
import br.com.lux.domain.car.Car;
import br.com.lux.domain.user.User;
import br.com.lux.services.car.CarService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/edit-car")
public class EditCarController
{
    @Autowired
    private CarService carService;

    @RequestMapping
    @GetMapping()
    public String editarCarGet(@RequestParam("id") Integer id, Model model, HttpSession session)
    {
        Car car = carService.findCarById(id);

        if(car == null)
        {
            return "redirect:/admin/all-cars";
        }

        model.addAttribute("car", car);

        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        return "admin/car/uptadecar";
    }

    @PostMapping()
    public String editarCarPost(@Valid @ModelAttribute Car car,
                                BindingResult bindingResult, HttpSession session, Model model)
    {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);

        if (bindingResult.hasErrors())
        {
            return "admin/car/uptadecar";
        }

        carService.registerCar(car);

        return "redirect:/admin/all-cars";
    }
}