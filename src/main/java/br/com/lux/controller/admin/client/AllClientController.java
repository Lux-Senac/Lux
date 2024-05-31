package br.com.lux.controller.admin.client;

import br.com.lux.domain.client.Client;
import br.com.lux.services.client.ClientService;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/all-clients")
public class AllClientController
{
    @Autowired
    private final ClientService clientService;

    public AllClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    private final Map<String, Object> response = new HashMap<>();

    @GetMapping
    public String page()
    {
        return "admin/client/gridclient";
    }

    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAllClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(value = "search[value]", required = false) String searchTerm
    )
    {
        Page<Client> clients;

        if (searchTerm != null && !searchTerm.isEmpty())
            clients = clientService.searchClients(searchTerm, page, size);
            else
                clients = clientService.findClientAll(page, size);

        response.put("data", clients.getContent());
        response.put("iTotalRecords", clients.getTotalElements());
        response.put("iTotalDisplayRecords", clients.getTotalElements());

        return ResponseEntity.ok(response);
    }
}
