package br.com.lux.controller.admin.car;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.user.User;
import br.com.lux.services.car.CarService;
import br.com.lux.services.exception.ServiceException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/cadastrar-carro")
public class RegisterCarController
{
    @Autowired
    private final CarService carService;

    public RegisterCarController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping
    @GetMapping
    public String registerCar(Model model, HttpSession session)
    {
        model.addAttribute("car", new Car());

        return "admin/car/registercar";
    }

    @PostMapping
    public String registerCarPost(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult, HttpSession session, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "admin/car/registercar";
        }
        else
        {
            try
            {
                carService.registerCar(car);

                return "redirect:/admin/all-cars";
            }
            catch (ServiceException e)
            {
                model.addAttribute("error", e.getMessage());
                return "admin/car/registercar";
            }
            catch (Exception e)
            {
                model.addAttribute("error", "Erro inesperado. Tente novamente.");

                return "admin/car/registercar";
            }
        }
    }
}
