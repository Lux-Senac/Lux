package br.com.lux.controller.user.login;

import br.com.lux.services.user.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/redefine-password")
public class RedefinePasswordController
{
    @Autowired
    private final UserService userService;

    public RedefinePasswordController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public String redefinePassword(HttpSession session)
    {
        session.setAttribute("trocarSenha", 0);

        return "login/redefinirSenha";
    }

    @PostMapping
    public String redefinePassword(String email, Model model , RedirectAttributes redirectAttributes,
             HttpSession session, String codigo, String password)
    {
        Integer trocarSenha = (Integer) session.getAttribute("trocarSenha");

        try
        {
            if (trocarSenha == null)
                throw new IllegalArgumentException("Email inválido!");

            if (trocarSenha == 0)
            {
                String emailValido = userService.validarEmail(email);

                if(emailValido.equals("Email não encontrado!") || emailValido.equals("Email inválido!"))
                {
                    redirectAttributes.addFlashAttribute("message", "Email inválido!");
                    session.removeAttribute("trocarSenha");

                    return "redirect:/redefine-password";
                }
                else
                {
                    userService.enviarCodigoVerificacao(email, session);
                    session.setAttribute("trocarSenha", 1);

                    return "login/redefinirSenha";
                }
            }
            else if (trocarSenha == 1)
            {
                int cont = (int) session.getAttribute("tentativasRedefinicao");

                if (userService.validarCodigoVerificacao(email, codigo, session))
                {
                    session.setAttribute("trocarSenha", 2);

                    return "login/redefinirSenha";
                }
                else
                {
                    cont++;

                    if (cont >= 3) {
                        session.removeAttribute("tentativasRedefinicao");
                        session.removeAttribute("trocarSenha");

                        redirectAttributes.addFlashAttribute("message", "Número máximo de tentativas excedido. Tente novamente.");

                        return "redirect:/redefine-password";
                    }
                    else
                    {
                        session.setAttribute("tentativasRedefinicao", cont);
                        model.addAttribute("message", "Código inválido. Tente novamente.");

                        return "login/redefinirSenha";
                    }
                }
            }
            else if (trocarSenha == 2)
            {
                if(password == null || password.isBlank())
                {
                    model.addAttribute("message", "Senha inválida!");
                    return "login/redefinirSenha";
                }

                userService.redefinirSenha(email, password);

                session.removeAttribute("trocarSenha");
                session.removeAttribute("tentativasRedefinicao");

                redirectAttributes.addFlashAttribute("message", "Senha redefinida com sucesso!");

                return "redirect:/login";
            }
        }
        catch (IllegalArgumentException e)
        {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/redefine-password";
        }

        return "redirect:/";
    }
}
