package br.com.lux.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @RequestMapping
    public String adminHome()
    {
        return "admin/adminHome";
    }
}
