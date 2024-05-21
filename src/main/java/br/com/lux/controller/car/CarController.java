package br.com.lux.controller.car;

import br.com.lux.domain.car.Car;
import br.com.lux.repository.car.CarRepository;

import br.com.lux.services.exception.ServiceException;
import org.jetbrains.annotations.NotNull;
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
    private final CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }

    @GetMapping("/find-all")
    public String findAllCars(Model model, RedirectAttributes redirectAttributes)
    {
        try
        {
            List<Car> cars = carRepository.findAll();
            model.addAttribute("cars", cars);

            return "offers/ofertas";
        }
        catch (ServiceException e)
        {
            redirectAttributes.addFlashAttribute("error", "Erro ao buscar carros");

            return "redirect:/home";
        }
        catch (Exception e)
        {
            redirectAttributes.addFlashAttribute("error", "Erro");

            return "redirect:/home";
        }
    }

    @GetMapping("/detalhes-carro")
    public String findById(@RequestParam("id") Integer id, Model model, RedirectAttributes redirectAttributes)
    {
        try
        {
            Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carro não encontrado"));

            model.addAttribute("car", car);
            return "cars/" + car.getPage();
        }
        catch (ServiceException e)
        {
            model.addAttribute("error", e.getMessage());

            return "redirect:/carros/find-all";
        }
        catch (Exception e)
        {
            model.addAttribute("error", "Erro");

            return "redirect:/carros/find-all";
        }
    }
}
