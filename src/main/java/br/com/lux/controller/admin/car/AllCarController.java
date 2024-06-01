package br.com.lux.controller.admin.car;

import br.com.lux.domain.car.Car;
import br.com.lux.services.car.CarService;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/all-cars")
public class AllCarController
{
    @Autowired
    private final CarService carService;

    public AllCarController(CarService carService) {
        this.carService = carService;
    }

    private final Map<String, Object> response = new HashMap<>();

    @GetMapping
    public String allCars()
    {
        return "admin/car/gridcar";
    }

    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> allCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(value = "search[value]", required = false) String searchTerm)
    {
        Page<Car> cars;

        if (searchTerm != null && !searchTerm.isEmpty()) {
            cars = carService.searchCars(searchTerm, page, size);
        } else {
            cars = carService.findCarAll(page, size);
        }

        response.put("data", cars.getContent());
        response.put("iTotalRecords", cars.getTotalElements());
        response.put("iTotalDisplayRecords", cars.getTotalElements());

        return ResponseEntity.ok(response);
    }
}
