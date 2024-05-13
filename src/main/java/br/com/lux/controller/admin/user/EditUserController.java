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
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/edit-user")
public class EditUserController
{
    @Autowired
    private final UserService userService;

    public EditUserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public String editUser(@RequestParam("id") Integer id, Model model, HttpSession session)
    {
        if (id == null)
        {
            return "redirect:/admin/all-users";
        }

        model.addAttribute("users", userService.findById(id));

        if (model.getAttribute("users") == null)
        {
            return "redirect:/admin/all-users";
        }

        model.addAttribute("user", session.getAttribute("user"));

        return "admin/user/edituser";
    }

    @PostMapping
    public String editUserPost(@Valid @ModelAttribute User users,
                               BindingResult bindingResult, HttpSession session, Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("user", session.getAttribute("user"));

            return "admin/user/edituser";
        }

        userService.createUser(users);

        return "redirect:/admin/all-users";
    }
}
