package br.com.lux.client;


import br.com.lux.controller.admin.client.AllClientController;
import br.com.lux.domain.client.Client;
import br.com.lux.services.client.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AllClientTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private AllClientController allClientController;

    @Test
    public void testGetAllClientsWithSearchTerm() {
        int page = 0;
        int size = 10;
        String searchTerm = "test";
        List<Client> clientList = Arrays.asList(new Client(), new Client());
        Page<Client> clientPage = new PageImpl<>(clientList);

        when(clientService.searchClients(searchTerm, page, size)).thenReturn(clientPage);

        ResponseEntity<Map<String, Object>> response = allClientController.getAllClients(page, size, searchTerm);

        verify(clientService, times(1)).searchClients(searchTerm, page, size);

        assertEquals(clientList, response.getBody().get("data"));
        assertEquals(clientPage.getTotalElements(), response.getBody().get("iTotalRecords"));
        assertEquals(clientPage.getTotalElements(), response.getBody().get("iTotalDisplayRecords"));
    }

    @Test
    public void testGetAllClientsWithoutSearchTerm() {
        // ... (Teste similar ao anterior, mas chamando clientService.findClientAll(page, size))
    }

    @Test
    public void testPage() {
        String viewName = allClientController.page();
        assertEquals("admin/client/gridclient", viewName);
    }
}

