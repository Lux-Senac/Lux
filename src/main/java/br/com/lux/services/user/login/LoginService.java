package br.com.lux.services.user.login;

import br.com.lux.domain.user.User;
import br.com.lux.repository.user.UserRepository;
import br.com.lux.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> authenticate(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}
