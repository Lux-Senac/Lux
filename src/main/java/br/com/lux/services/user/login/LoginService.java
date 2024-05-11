package br.com.lux.services.user.login;

import br.com.lux.domain.client.Client;
import br.com.lux.domain.user.User;
import br.com.lux.repository.user.UserRepository;
import br.com.lux.services.gravatar.GravatarService;
import br.com.lux.services.user.UserService;
import br.com.lux.domain.user.UserType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> authenticate(String email, String password)
    {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent() && PasswordUtils.checkPassword(password, optionalUser.get().getPassword()))
            return Optional.of(optionalUser.get());

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
            user.setPassword(PasswordUtils.encryptPassword(user.getPassword()));

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
