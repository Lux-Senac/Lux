package br.com.lux.controller.user.login;

import br.com.lux.domain.user.User;
import br.com.lux.services.user.UserService;
import br.com.lux.domain.user.UserType;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController
{
    @Autowired
    private UserService userService;

    @RequestMapping
    @GetMapping
    public String login()
    {
        return "login/login";
    }

    @PostMapping
    public String loginPost(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        Optional<User> optionalUser = userService.authenticate(email, password);

        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            session.setAttribute("user", user); // Armazena o usuário na sessão

            if(user.getTipo() == UserType.Admin) {
                return "redirect:/admin";
            }
            model.addAttribute("message", "Logado com Sucesso!");
            return "home/index";
        }

        model.addAttribute("message", "Credenciais Inválidas!");
        return "login/login";
    }
}
