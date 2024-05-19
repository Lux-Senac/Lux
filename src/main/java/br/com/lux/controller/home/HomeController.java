package br.com.lux.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    @GetMapping("/home")
    public String home() {
        return "home/index";
    }

    @GetMapping("/sobre")
    public String sobre() {
        return "about/sobre";
    }
}
