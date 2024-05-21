package br.com.lux.controller.admin.user;

import br.com.lux.domain.car.Car;
import br.com.lux.domain.user.User;
import br.com.lux.services.client.ClientService;
import br.com.lux.services.exception.ServiceException;
import br.com.lux.services.user.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/edit-user")
public class EditUserController
{
    @Autowired
    private final UserService userService;

    @Autowired
    private final ClientService clientService;

    public EditUserController(UserService userService, ClientService clientService)
    {
        this.userService = userService;
        this.clientService = clientService;
    }

    @GetMapping
    public String editUser(@RequestParam("userid") Integer id, Model model, HttpSession session, RedirectAttributes redirectAttributes)
    {
        try
        {
            if (id == null)
            {
                return "redirect:/admin/all-users";
            }

            User user = userService.findById(id);

            if (user == null)
            {
                return "redirect:/admin/all-users";
            }

            if (user.getCliente() == null)
            {
                model.addAttribute("clients", clientService.findByUsersIsNull());
            }

            model.addAttribute("users", user);
            model.addAttribute("user", session.getAttribute("user"));

            return "admin/user/updateuser";
        }
        catch (ServiceException e)
        {
            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/admin/all-users";
        }
        catch (Exception e)
        {
            model.addAttribute("error", "Erro ao editar usuário! " + e.getMessage());
            return "admin/user/updateuser";
        }
    }

    @PostMapping
    public String editUserPost(@Valid @ModelAttribute("users") User users,
                               BindingResult bindingResult, HttpSession session, Model model)
    {
        try
        {
            if(bindingResult.hasErrors())
            {
                model.addAttribute("user", session.getAttribute("user"));
                model.addAttribute("clients", clientService.findByUsersIsNull());

                return "admin/user/updateuser";
            }

            userService.createUserAdmin(users);

            return "redirect:/admin/all-users";
        }
        catch (ServiceException e)
        {
            model.addAttribute("error", e.getMessage());
            return "admin/user/updateuser";
        }
        catch (Exception e)
        {
            model.addAttribute("error", "Erro ao editar usuário! " + e.getMessage());
            return "admin/user/updateuser";
        }
    }
}
