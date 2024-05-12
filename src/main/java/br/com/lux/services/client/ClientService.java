package br.com.lux.services.client;

import br.com.lux.domain.client.Client;

import java.util.List;

public interface ClientService
{
    Client registerClient(Client client);

    Client findClientById(Integer id);

    List<Client> findAllClients();
}
