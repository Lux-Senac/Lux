package br.com.lux.client;


import br.com.lux.controller.admin.client.DeleteClientController;
import br.com.lux.services.client.ClientService;

import br.com.lux.services.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private DeleteClientController deleteClientController;

    @Test
    void testDeleteClientSuccess() {
        Integer clientId = 1;
        String expectedRedirect = "redirect:/admin/all-clients";

        String result = deleteClientController.deleteClient(clientId);

        verify(clientService, times(1)).deleteClient(clientId);
        assertEquals(expectedRedirect, result);
    }

    @Test
    void testDeleteClientNullId() {
        String expectedRedirect = "redirect:/admin/all-clients";
        String result = deleteClientController.deleteClient(null);
        assertEquals(expectedRedirect, result);
        verify(clientService, never()).deleteClient(any()); // Verifica se o método não foi chamado
    }

}
