package br.com.lux.controller.admin.user;

import br.com.lux.services.exception.ServiceException;
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
        model.addAttribute("user", session.getAttribute("user"));

        try
        {
            model.addAttribute("users", userService.findAllUsers());

            return "admin/user/griduser";
        }
        catch (ServiceException e)
        {
            model.addAttribute("error", e.getMessage());

            return "admin/user/griduser";
        }
        catch (Exception e)
        {
            model.addAttribute("error", "Erro ao buscar usuários.");

            return "admin/user/griduser";
        }
    }
}
