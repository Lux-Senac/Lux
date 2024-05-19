package br.com.lux.controller.user.login;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController extends HttpServlet
{
    @GetMapping
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/home";
    }
}
