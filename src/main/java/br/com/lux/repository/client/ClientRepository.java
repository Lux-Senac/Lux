package br.com.lux.repository.client;

import br.com.lux.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>
{
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    List<Client> findByUsersIsNull();
}
