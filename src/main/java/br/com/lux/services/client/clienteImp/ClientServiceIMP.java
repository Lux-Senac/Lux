package br.com.lux.services.client.clienteImp;

import br.com.lux.domain.client.Client;
import br.com.lux.domain.user.User;
import br.com.lux.repository.client.ClientRepository;
import br.com.lux.services.client.ClientService;
import br.com.lux.services.exception.ServiceException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Transactional
    public Client registerClient(Client client)
    {
        try
        {
            Set<ConstraintViolation<Client>> violations = validator.validate(client);

            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }

            return clientRepository.save(client);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new ServiceException("Erro ao salvar o cliente! " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteClient(Integer id)
    {
        try
        {
            Client client = clientRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("Cliente não encontrado com ID: " + id));

            for (User user : client.getUsers()) {
                user.setCliente(null);
            }

            clientRepository.delete(client);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new ServiceException("Erro ao deletar o cliente! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Client> findAllClients()
    {
        try
        {
            return clientRepository.findAll();
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao buscar todos os clientes! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Client> findClientAll(int page, int size)
    {
        try
        {
            return clientRepository.findAll(PageRequest.of(page, size));
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao buscar todos os clientes! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Client> searchClients(String searchTerm, int page, int size)
    {
        try
        {
            return clientRepository.findByNomeContainingIgnoreCase(searchTerm, PageRequest.of(page, size));
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao buscar clientes! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Client findClientById(Integer id)
    {
        try
        {
            return clientRepository.findById(id).orElse(null);
        }
        catch (Exception e)
        {
            throw new ServiceException("Cliente não encontrado com ID: " + id);
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Client> findByUsersIsNull()
    {
        try
        {
            return clientRepository.findByUsersIsNull();
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao buscar clientes sem usuários! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public long countClients()
    {
        try
        {
            return clientRepository.count();
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao contar clientes! " + e.getMessage());
        }
    }
}
