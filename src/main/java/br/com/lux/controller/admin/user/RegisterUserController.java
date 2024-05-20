package br.com.lux.controller.admin.user;

import br.com.lux.domain.user.User;

import br.com.lux.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/registerUser")
public class RegisterUserController
{
    @Autowired
    private final UserService userService;

    public RegisterUserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public String registerSales(Model model, HttpSession session)
    {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("users", new User());

        return "admin/user/registeruser";
    }

    @PostMapping
    public String registerSalesPost(@Valid @ModelAttribute("users") User users, BindingResult bindingResult, HttpSession session, Model model)
    {
        model.addAttribute("user", session.getAttribute("user"));

        if (bindingResult.hasErrors())
        {
            return "admin/user/registeruser";
        }
        else
        {
            try
            {
                userService.createUserAdmin(users);

                return "redirect:/admin/all-users";
            }
            catch (SecurityException e)
            {
                model.addAttribute("error", e.getMessage());
                return "admin/user/registeruser";
            }
        }
    }
}
