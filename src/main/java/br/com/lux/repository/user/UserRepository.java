package br.com.lux.repository.user;

import br.com.lux.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    Optional<User> findByEmail(String email);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    Optional<User> findByUsername(String username);
}
