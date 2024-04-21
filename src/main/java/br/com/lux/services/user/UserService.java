package br.com.lux.services.user;

import br.com.lux.domain.user.User;

import java.util.Optional;

public interface UserService
{
    Optional<User> authenticate(String email, String password);

    Optional<User> createUser(String username, String email, String password);
}
