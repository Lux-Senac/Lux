package br.com.lux.controller.admin.user;

import br.com.lux.services.user.UserService;

import jakarta.servlet.http.HttpSession;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/delete-user")
public class DeleteUserController
{
    @Autowired
    private final UserService userService;

    public DeleteUserController(UserService userService)
    {
        this.userService = userService;
    }

    @DeleteMapping
    public String deleteUser(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes, HttpSession session)
    {
        redirectAttributes.addAttribute("user", session.getAttribute("user"));

        try
        {
            if(id == null || id == 1)
            {
                redirectAttributes.addAttribute("error", "Usuário não encontrado. Tente novamente. Ou usuário administrador não pode ser deletado.");
                return "redirect:/admin";
            }

            userService.deleteById(id);

            return "redirect:/admin/all-users";
        }
        catch (ServiceException e)
        {
            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/admin";
        }
        catch (Exception e)
        {
            redirectAttributes.addAttribute("error", "Erro ao deletar usuário. Tente novamente.");
            return "redirect:/admin";
        }
    }
}
