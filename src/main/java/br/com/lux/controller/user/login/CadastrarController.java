package br.com.lux.controller.user.login;

import br.com.lux.domain.user.User;
import br.com.lux.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@RequestMapping("/cadastro")
public class CadastrarController
{
    @Autowired
    private UserService userService;

    @RequestMapping
    @GetMapping
    public String cadastrar()
    {
        return "login/cadastrar";
    }

    @PostMapping
    public String cadastrarPost(@RequestParam String username, @RequestParam String email, @RequestParam String password, Model model)
    {
        Optional<User> optionalUser = userService.createUser(username, email, password);
        
        if(optionalUser.isPresent())
        {
            model.addAttribute("message", "Usuário cadastrado com sucesso!");
            return "login/login";
        }

        model.addAttribute("message", "Usuário já cadastrado!");
        return "login/cadastrar";
    }
}
