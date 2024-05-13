package br.com.lux.controller.admin.user;

import br.com.lux.services.user.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/all-users")
public class AllUserController
{
    @Autowired
    private final UserService userService;

    public AllUserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public String allUsers(Model model, HttpSession session)
    {
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("user", session.getAttribute("user"));

        return "admin/user/griduser";
    }
}
