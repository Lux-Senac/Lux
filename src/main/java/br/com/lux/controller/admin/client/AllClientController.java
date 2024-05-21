package br.com.lux.controller.admin.client;

import br.com.lux.services.client.ClientService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/all-clients")
public class AllClientController
{
    @Autowired
    private final ClientService clientService;

    public AllClientController(ClientService clientService)
    {
        this.clientService = clientService;
    }

    @GetMapping
    public String allClient(Model model, HttpSession session)
    {
        try
        {
            model.addAttribute("clients", clientService.findAllClients());
            model.addAttribute("user", session.getAttribute("user"));

            return "admin/client/gridclient";
        }
        catch(SecurityException e)
        {
            model.addAttribute("error", e.getMessage());
            return "admin/client/gridclient";
        }
        catch (Exception e)
        {
            model.addAttribute("error", "Erro inesperado!");
            return "admin/client/gridclient";
        }
    }
}
