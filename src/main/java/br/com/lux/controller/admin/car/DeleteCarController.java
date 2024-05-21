package br.com.lux.controller.admin.car;

import br.com.lux.controller.car.CarController;
import br.com.lux.services.car.CarService;
import br.com.lux.services.exception.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/delete-car")
public class DeleteCarController
{
    @Autowired
    private final CarService carService;

    public DeleteCarController(CarService carService) {
        this.carService = carService;
    }

    @DeleteMapping
    public String deleteCar(@RequestParam("id") Integer id)
    {
        if(id == null)
        {
            return "redirect:/admin/all-cars";
        }

        carService.deleteCar(id);

        return "redirect:/admin/all-cars";
    }
}
