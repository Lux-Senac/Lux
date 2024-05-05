package br.com.lux.services.client;

import br.com.lux.domain.client.Client;

public interface ClientService
{
    Client registerClient(Client client);

    Client findClientById(Integer id);
}
