package br.com.lux.controller.admin.client;

import br.com.lux.domain.client.Client;
import br.com.lux.services.client.ClientService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/edit-client")
public class EditClientController
{
    @Autowired
    private final ClientService clientService;

    public EditClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String editClient(@RequestParam("id") Integer id, Model model, HttpSession session)
    {
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("clients", clientService.findClientById(id));

        return "admin/client/uptadeclient";
    }

    @PostMapping
    public String editClientPost(@Valid @ModelAttribute Client client,
                                 BindingResult bindingResult, HttpSession session, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("user", session.getAttribute("user"));

            return "admin/client/uptadeclient";
        }

        clientService.registerClient(client);

        return "redirect:/admin/all-clients";
    }
}
