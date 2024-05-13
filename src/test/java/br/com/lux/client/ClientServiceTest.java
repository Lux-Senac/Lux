package br.com.lux.client;

import br.com.lux.domain.client.Client;
import br.com.lux.domain.client.ContactPreference;
import br.com.lux.domain.client.Countries;
import br.com.lux.repository.client.ClientRepository;
import br.com.lux.services.client.ClientService;
import br.com.lux.services.client.clienteImp.ClientServiceIMP;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.validation.ConstraintViolationException;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private Validator validator;

    private ClientService clientService;

    @Before
    public void setUp() {
        clientService = new ClientServiceIMP(clientRepository, validator);
    }

    @Test
    public void testRegisterClientSuccess() {
        // Arrange
        Client client = new Client();
        client.setNome("John");
        client.setSobrenome("");
        client.setPais(Countries.BRASIL);
        client.setCep("12345678");
        client.setEmail("");
        client.setContato("0");
        client.setPreferenciacontato(ContactPreference.EMAIL);

        when(validator.validate(client)).thenReturn(Collections.emptySet());

        // Act
        Client result = clientService.registerClient(client);

        // Assert
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(clientRepository).save(clientCaptor.capture());
        Client savedClient = clientCaptor.getValue();
        assertEquals(client, savedClient);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testRegisterClientWithInvalidData() {
        // Arrange
        Client client = new Client();
        // Deixe alguns campos obrigatórios vazios ou com valores inválidos

        Set<ConstraintViolation<Client>> violations = Collections.singleton(mock(ConstraintViolation.class)); // Cria um mock de violação
        when(validator.validate(client)).thenReturn(violations); // Simula o comportamento do Validator

        // Act
        clientService.registerClient(client);

        // Assert
        // A exceção ConstraintViolationException deve ser lançada
    }

    @Test
    public void testFindClientByIdSuccess() {
        // Arrange
        Integer clientId = 1;
        Client client = new Client();
        client.setId(clientId);
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        // Act
        Client result = clientService.findClientById(clientId);

        // Assert
        assertEquals(client, result);
    }

    @Test
    public void testFindClientByIdWithInvalidId() {
        // Arrange
        Integer invalidClientId = -1;
        when(clientRepository.findById(invalidClientId)).thenReturn(Optional.empty());

        // Act
        Client result = clientService.findClientById(invalidClientId);

        // Assert
        assertNull(result);
    }
}