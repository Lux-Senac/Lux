package br.com.lux.controller.car;

import br.com.lux.domain.car.Car;
import br.com.lux.repository.car.CarRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/carros")
public class CarController
{
    private final CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }

    @GetMapping("/find-all")
    public String findAllCars(Model model)
    {
        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);

        return "offers/ofertas";
    }

    @GetMapping("/detalhes-carro")
    public String findById(@RequestParam("id") Integer id, Model model)
    {
        Car car = carRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Carro não encontrado"));
        model.addAttribute("car", car);

        return "cars/" + car.getPage();
    }
}
