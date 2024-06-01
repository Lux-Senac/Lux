package br.com.lux.repository.user;

import br.com.lux.domain.user.User;

import br.com.lux.domain.user.UserType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    Optional<User> findByEmail(String email);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM USR u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :term, '%'))")
    Page<User> findByUsernameOrEmailContaining(String term, Pageable pageable);

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    List<User> findByTipo(UserType tipo);
}
