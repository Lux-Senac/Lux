package br.com.lux.controller.error;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AllErrorController implements ErrorController
{
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model)
    {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value())
            {
                model.addAttribute("errorCode", statusCode);
                model.addAttribute("errorMessage", "Página não encontrada.");

                return "error/404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorCode", statusCode);
                model.addAttribute("errorMessage", "Erro interno do servidor.");

                return "error/500";
            }
        }
        return "error";
    }
}
