package br.com.lux.controller.user.login;

import br.com.lux.domain.user.User;
import br.com.lux.services.email.Email;
import br.com.lux.services.exception.ServiceException;
import br.com.lux.services.user.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/cadastro")
public class CadastrarController
{
    @Autowired
    private UserService userService;

    @GetMapping
    public String cadastrar(Model model)
    {
        try
        {
            model.addAttribute("user", new User());

            return "login/cadastrar";
        }
        catch (ServiceException e)
        {
            model.addAttribute("message", e.getMessage());
            return "login/cadastrar";
        }
        catch (Exception e)
        {
            model.addAttribute("message", "Erro ao cadastrar usuário!");
            return "login/cadastrar";
        }
    }

    @PostMapping
    public String cadastrarPost(Model model, @Valid @ModelAttribute User user, BindingResult bindingResult,  RedirectAttributes redirectAttributes)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("message", "Erro ao cadastrar usuário!");
            return "/login/cadastrar";
        }

        Optional<User> optionalUser = userService.createUser(user);

        if(optionalUser.isPresent())
        {
            redirectAttributes.addAttribute("message", "Usuário cadastrado com sucesso!");
            return "redirect:/login";
        }

        model.addAttribute("message", "Usuário já cadastrado!");

        return "login/cadastrar";
    }
}
