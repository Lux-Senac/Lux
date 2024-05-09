package br.com.lux.services.user.login;

import br.com.lux.domain.client.Client;
import br.com.lux.domain.user.User;
import br.com.lux.repository.user.UserRepository;
import br.com.lux.services.gravatar.GravatarService;
import br.com.lux.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lux.domain.user.UserType;
import java.util.Optional;

@Service
public class LoginService implements UserService
{
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final GravatarService gravatarService;

    public LoginService(UserRepository userRepository, GravatarService gravatarService) {
        this.userRepository = userRepository;
        this.gravatarService = gravatarService;
    }

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

    @Override
    public Optional<User> createUser(User user)
    {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent())
        {
            return Optional.empty();
        }
        else
        {
            String urlAvatar = gravatarService.getGravatarUrl(user.getEmail().toLowerCase());
            user.setUrlavatar(urlAvatar);

            user.setTipo(UserType.CLIENTE);

            userRepository.save(user);

            return Optional.of(user);
        }
    }

    @Override
    public void changeClientId(Integer id, Client client)
    {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent())
        {
            User user = optionalUser.get();
            user.setCliente(client);
            userRepository.save(user);
        }
    }
}
