package br.com.lux.controller.admin;

import br.com.lux.domain.user.User;
import br.com.lux.services.sales.SalesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController
{
    @RequestMapping
    public String adminHome(Model model, HttpSession session)
    {
        model.addAttribute("user", session.getAttribute("user"));

        return "admin/adminHome";
    }
}
