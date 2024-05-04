package br.com.lux.controller.carrinho;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
public class Carrinho {

    @GetMapping("/carrinho")
    public ModelAndView chamarCarrinho() {
        ModelAndView mv = new ModelAndView("resources/templates/cart/cart");
        return mv;
    }

    @GetMapping("/cart")
    public ModelAndView adicionarCarrinho(@PathVariable int id) {
        ModelAndView mv = new ModelAndView("cart/cart");

        return mv;
    }
}

