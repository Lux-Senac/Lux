package br.com.lux.repository.client;

import br.com.lux.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>
{
    List<Client> findByUsersIsNull();
}
