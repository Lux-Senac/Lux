package br.com.lux.controller.admin.car;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.car.CarPageType;
import br.com.lux.services.car.CarService;
import br.com.lux.services.exception.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@Component("adminCarController")
@RequestMapping("/admin/carros")
public class CarController
{
    @Autowired
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/carTypeCounts")
    @ResponseBody
    public Map<CarPageType, Long> getCarTypeCounts() {
        return carService.getCarTypeCounts();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Car getCarById(@PathVariable("id") Integer id) throws ServiceException
    {
        Car car = carService.findCarById(id);

        if (car == null) {
            throw new ServiceException("Carro não encontrado.");
        }
        return car;
    }
}
