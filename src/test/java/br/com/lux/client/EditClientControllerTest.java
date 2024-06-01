package br.com.lux.client;

import br.com.lux.controller.admin.client.EditClientController;
import br.com.lux.domain.client.Client;
import br.com.lux.services.client.ClientService;
import br.com.lux.services.exception.ServiceException;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private EditClientController editClientController;

    @Test
    void testEditClientGetMappingSuccess() throws ServiceException {
        int clientId = 1;
        Client client = new Client();
        when(clientService.findClientById(clientId)).thenReturn(client);

        String result = editClientController.editClient(clientId, model, session);

        verify(clientService, times(1)).findClientById(clientId);
        verify(model, times(1)).addAttribute("clients", client);
        assertEquals("admin/client/uptadeclient", result);
    }

    @Test
    void testEditClientGetMappingServiceException() throws ServiceException {
        int clientId = 1;
        when(clientService.findClientById(clientId)).thenThrow(ServiceException.class);

        String result = editClientController.editClient(clientId, model, session);

        verify(clientService, times(1)).findClientById(clientId);
        assertEquals("redirect:/admin/all-clients", result);
    }

    @Test
    void testEditClientPostMappingSuccess() throws ServiceException {
        Client client = new Client(); // Crie uma instância de cliente com dados válidos
        when(bindingResult.hasErrors()).thenReturn(false);

        String result = editClientController.editClientPost(client, bindingResult, session, model);

        verify(clientService, times(1)).registerClient(client);
        assertEquals("redirect:/admin/all-clients", result);
    }

    @Test
    void testEditClientPostMappingBindingResultErrors() {
        Client client = new Client();
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = editClientController.editClientPost(client, bindingResult, session, model);

        verify(clientService, never()).registerClient(any());
        assertEquals("admin/client/uptadeclient", result);
    }


}

