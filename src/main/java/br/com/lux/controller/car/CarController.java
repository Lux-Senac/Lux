package br.com.lux.controller.car;

import br.com.lux.domain.car.Car;
import br.com.lux.repository.car.CarRepository;

import br.com.lux.services.car.CarService;
import br.com.lux.services.exception.ServiceException;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Component("publicCarController")
@RequestMapping("/carros")
public class CarController
{
    @Autowired
    private final CarService carService;

    @Autowired
    public CarController(CarService carService)
    {
        this.carService = carService;
    }

    @GetMapping("/find-all")
    public String findAllCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model, RedirectAttributes redirectAttributes
    )
    {
        try
        {
            Page<Car> cars = carService.findCarAll(page, size);
            model.addAttribute("cars", cars);
            model.addAttribute("currentPage", page);
            return "offers/ofertas";
        }
        catch (ServiceException e)
        {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/home";
        }
    }

    @GetMapping("/detalhes-carro")
    public String findById(@RequestParam("id") Integer id, Model model)
    {
        try
        {
            Car car = carService.findCarById(id);

            model.addAttribute("car", car);
            return "cars/" + car.getPage();
        }
        catch (ServiceException e)
        {
            model.addAttribute("error", e.getMessage());

            return "redirect:/carros/find-all";
        }
    }
}
