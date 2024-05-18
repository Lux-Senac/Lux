package br.com.lux.services.client.clienteImp;

import br.com.lux.domain.client.Client;
import br.com.lux.repository.client.ClientRepository;
import br.com.lux.services.client.ClientService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;

@Service
public class ClientServiceIMP implements ClientService
{
    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    private final Validator validator;

    public ClientServiceIMP(ClientRepository clientRepository, Validator validator) {
        this.clientRepository = clientRepository;
        this.validator = validator;
    }

    @Override
    public Client registerClient(Client client)
    {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Integer id)
    {
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Client> findAllClients()
    {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Client findClientById(Integer id)
    {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Client> findByUsersIsNull()
    {
        return clientRepository.findByUsersIsNull();
    }
}
