package br.com.lux.controller.admin.client;

import br.com.lux.domain.client.Client;
import br.com.lux.services.client.ClientService;

import br.com.lux.services.exception.ServiceException;
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
        try
        {
            model.addAttribute("user", session.getAttribute("user"));
            model.addAttribute("clients", clientService.findClientById(id));

            return "admin/client/uptadeclient";
        }
        catch (ServiceException e)
        {

            return "redirect:/admin/all-clients";
        }
        catch (Exception e)
        {
            return "redirect:/admin/all-clients";
        }
    }

    @PostMapping
    public String editClientPost(@Valid @ModelAttribute Client client,
                                 BindingResult bindingResult, HttpSession session, Model model)
    {
        model.addAttribute("user", session.getAttribute("user"));

        if (bindingResult.hasErrors())
        {
            return "admin/client/uptadeclient";
        }
        else
        {
            try
            {
                clientService.registerClient(client);

                return "redirect:/admin/all-clients";
            }
            catch (ServiceException e)
            {
                model.addAttribute("error", e.getMessage());
                return "admin/client/uptadeclient";
            }
            catch (Exception e)
            {
                model.addAttribute("error", "Erro inesperado!");
                return "admin/client/uptadeclient";
            }
        }
    }
}
