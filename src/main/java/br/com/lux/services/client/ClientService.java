package br.com.lux.services.client;

import br.com.lux.domain.client.Client;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService
{
    Client registerClient(Client client);

    Client findClientById(Integer id);

    List<Client> findAllClients();

    Page<Client> findClientAll(int page, int size);

    Page<Client> searchClients(String searchTerm, int page, int size);

    void deleteClient(Integer id);

    List<Client> findByUsersIsNull();

    long countClients();
}
