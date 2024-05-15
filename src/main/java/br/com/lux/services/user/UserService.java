package br.com.lux.services.user;

import br.com.lux.domain.client.Client;
import br.com.lux.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService
{
    Optional<User> authenticate(String email, String password);

    Optional<User> createUser(User user);

    void changeClientId(Integer id, Client client);

    List<User> findAllUsers();

    void deleteById(Integer id);

    User findById(Integer id);

    void createUserAdmin(User user);
}
