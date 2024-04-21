package br.com.lux.controller.admin.car;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/cadastrar-carro")
public class RegisterCarController
{
    @RequestMapping
    @GetMapping
    public String registerCar()
    {
        return "admin/car/registercar";
    }
}
