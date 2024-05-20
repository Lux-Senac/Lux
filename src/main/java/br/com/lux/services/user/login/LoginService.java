package br.com.lux.services.user.login;

import br.com.lux.domain.client.Client;
import br.com.lux.domain.user.User;
import br.com.lux.repository.user.UserRepository;
import br.com.lux.services.email.Email;
import br.com.lux.services.email.EmailService;
import br.com.lux.services.gravatar.GravatarService;
import br.com.lux.services.user.UserService;
import br.com.lux.domain.user.UserType;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService implements UserService
{
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final GravatarService gravatarService;

    @Autowired
    private final EmailService emailService;

    public LoginService(UserRepository userRepository, GravatarService gravatarService, EmailService emailService) {
        this.userRepository = userRepository;
        this.gravatarService = gravatarService;
        this.emailService = emailService;
    }

    @Override
    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
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

    @Override
    public void deleteById(Integer id)
    {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public User findById(Integer id)
    {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void createUserAdmin(User user)
    {
        if(user.getUrlavatar() == null || user.getUrlavatar().isEmpty())
            user.setUrlavatar(gravatarService.getGravatarUrl(user.getEmail().toLowerCase()));

        if(user.getTipo() == null)
            user.setTipo(UserType.CLIENTE);

        if(user.getId() == 1)
            user.setTipo(UserType.ADMIN);


        userRepository.save(user);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String validarEmail(String email)
    {
        if(email == null || email.isBlank())
            return "Email inválido!";

        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty())
            return "Email não cadastrado!";

        return null;
    }

    @Override
    public void enviarCodigoVerificacao(String email, HttpSession session)
    {
        String codigo = PasswordUtils.generateRandomCode();

        session.setAttribute(email + "_verification_code", codigo);

        Email emailM = new Email(email, "Código de Verificação", "Seu código de verificação é: " + codigo);

        emailService.sendEmail(emailM);
    }

    @Override
    public boolean validarCodigoVerificacao(String email, String codigo, HttpSession session)
    {
        String codigoSalvo = (String) session.getAttribute(email + "_verification_code");

        if (codigoSalvo != null && codigoSalvo.equals(codigo))
        {
            session.removeAttribute(email + "_verification_code");
            return true;
        }

        return false;
    }

    @Override
    public void redefinirSenha(String email, String novaSenha)
    {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o email: " + email));

        user.setPassword(PasswordUtils.encryptPassword(novaSenha));

        userRepository.save(user);
    }
}
