package br.com.lux.controller.admin.client;

import br.com.lux.services.client.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/delete-client")
public class DeleteClientController
{
    @Autowired
    private final ClientService clientService;

    public DeleteClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @DeleteMapping
    public String deleteClient(@RequestParam("id") Integer id)
    {
        if(id == null)
        {
            return "redirect:/admin/all-clients";
        }

        clientService.deleteClient(id);

        return "redirect:/admin/all-clients";
    }
}
