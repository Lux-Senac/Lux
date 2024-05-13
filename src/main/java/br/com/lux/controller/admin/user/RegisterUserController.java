package br.com.lux.controller.admin.user;

import br.com.lux.domain.car.Car;
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
@RequestMapping("/admin/register-user")
public class RegisterUserController
{
    @Autowired
    private final UserService userService;

    public RegisterUserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public String registerUser(Model model, HttpSession session)
    {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("users", new User());

        return "admin/user/registeruser";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute User users, BindingResult bindingResult, HttpSession session, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("user", session.getAttribute("user"));

            return "admin/user/registeruser";
        }

        userService.createUser(users);

        return "redirect:/admin/all-users";
    }
}
