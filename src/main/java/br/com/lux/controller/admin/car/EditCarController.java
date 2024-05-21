package br.com.lux.controller.admin.car;

import br.com.lux.controller.car.CarController;
import br.com.lux.domain.car.Car;
import br.com.lux.domain.user.User;
import br.com.lux.services.car.CarService;

import br.com.lux.services.exception.ServiceException;
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
    private final CarService carService;

    public EditCarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String editarCarGet(@RequestParam("id") Integer id, Model model, HttpSession session)
    {
        model.addAttribute("user", session.getAttribute("user"));

        try
        {
            if (id == null)
            {
                return "redirect:/admin/all-cars";
            }

            Car car = carService.findCarById(id);

            if(car == null)
            {
                return "redirect:/admin/all-cars";
            }

            model.addAttribute("car", car);

            return "admin/car/uptadecar";
        }
        catch(ServiceException e)
        {
            model.addAttribute("error", e.getMessage());

            return "redirect:/admin/all-cars";
        }
        catch (Exception e)
        {
            model.addAttribute("error", "Erro ao buscar carro");

            return "redirect:/admin/all-cars";
        }
    }

    @PostMapping()
    public String editarCarPost(@Valid @ModelAttribute Car car,
                                BindingResult bindingResult, HttpSession session, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("user", session.getAttribute("user"));

            return "admin/car/uptadecar";
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

                return "admin/car/uptadecar";
            }
            catch (Exception e)
            {
                model.addAttribute("error", "Erro inesperado. Tente novamente.");

                return "admin/car/uptadecar";
            }
        }

    }
}
