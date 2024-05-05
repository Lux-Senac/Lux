package br.com.lux.services.client.clienteImp;

import br.com.lux.domain.client.Client;
import br.com.lux.repository.client.ClientRepository;
import br.com.lux.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceIMP implements ClientService
{
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client registerClient(Client client)
    {
        return clientRepository.save(client);
    }

    @Override
    public Client findClientById(Integer id)
    {
        return clientRepository.findById(id).orElse(null);
    }
}
