package br.com.lux.services.user.login;

import br.com.lux.domain.client.Client;
import br.com.lux.domain.user.User;
import br.com.lux.repository.user.UserRepository;
import br.com.lux.services.email.Email;
import br.com.lux.services.email.EmailService;
import br.com.lux.services.exception.ServiceException;
import br.com.lux.services.gravatar.GravatarService;
import br.com.lux.services.user.UserService;
import br.com.lux.domain.user.UserType;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<User> findAllUsers()
    {
        try
        {
            return userRepository.findAll();
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao buscar todos os usuários! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<User> authenticate(String email, String password)
    {
        try
        {
            Optional<User> optionalUser = userRepository.findByEmail(email);

            if(optionalUser.isPresent() && PasswordUtils.checkPassword(password, optionalUser.get().getPassword()))
                return Optional.of(optionalUser.get());

            return Optional.empty();
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao autenticar usuário! " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user)
    {
        try
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
        catch (DataIntegrityViolationException e)
        {
            throw new ServiceException("Erro ao criar usuário! " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void changeClientId(Integer id, Client client)
    {
        try
        {
            Optional<User> optionalUser = userRepository.findById(id);
            if(optionalUser.isPresent())
            {
                User user = optionalUser.get();
                user.setCliente(client);
                userRepository.save(user);
            }
        }
        catch (DataIntegrityViolationException e)
        {
            throw new ServiceException("Erro ao alterar id do cliente! " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteById(Integer id)
    {
       try
       {
            if (id == null || id == 1)
                throw new ServiceException("Id inválido!");

            userRepository.deleteById(id);
       }
       catch (ServiceException e)
       {
            throw new ServiceException("Erro ao deletar usuário por id! " + e.getMessage());
       }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public User findById(Integer id)
    {
        try
        {
            if (id == null)
                throw new ServiceException("Id inválido!");

            return userRepository.findById(id).orElse(null);
        }
        catch (ServiceException e)
        {
            throw new ServiceException("Erro ao buscar usuário por id! " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void createUserAdmin(User user)
    {
        try
        {
            if (userRepository.findByEmail(user.getEmail()).isPresent() && user.getId() == null)
                throw new ServiceException("Já existe um usuário com este email!");

            if (userRepository.findByUsername(user.getUsername()).isPresent() && user.getId() == null)
                throw new ServiceException("Já existe um usuário com este username!");

            if (user.getUrlavatar() == null || user.getUrlavatar().isEmpty())
                user.setUrlavatar(gravatarService.getGravatarUrl(user.getEmail().toLowerCase()));

            if (user.getTipo() == null)
                user.setTipo(UserType.CLIENTE);

            if (user.getId() != null)
                if (user.getId() == 1)
                    user.setTipo(UserType.ADMIN);

            userRepository.save(user);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new ServiceException("Erro ao criar usuário! + " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public String validarEmail(String email)
    {
        try
        {
            if(email == null || email.isBlank())
                return "Email inválido!";

            Optional<User> optionalUser = userRepository.findByEmail(email);

            if(optionalUser.isEmpty())
                return "Email não cadastrado!";

            return "null";
        }
        catch (DataIntegrityViolationException e)
        {
            throw new ServiceException("Erro ao validar email! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void enviarCodigoVerificacao(String email, HttpSession session)
    {
        try
        {
            String codigo = PasswordUtils.generateRandomCode();

            session.setAttribute(email + "_verification_code_", codigo);

            Email emailM = new Email(email, "Código de Verificação", "Seu código de verificação é: " + codigo);

            System.out.println("Código de verificação: " + codigo);

            emailService.sendEmail(emailM);
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao enviar código de verificação! " + e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean validarCodigoVerificacao(String email, String codigo, HttpSession session)
    {
        try
        {
            String codigoSalvo = (String) session.getAttribute(email + "_verification_code_");

            if (codigo.equals(codigoSalvo))
            {
                session.removeAttribute(email + "_verification_code_");
                return true;
            }

            return false;
        }
        catch (Exception e)
        {
            throw new ServiceException("Erro ao validar código de verificação! " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void redefinirSenha(String email, String novaSenha)
    {
        try
        {
            Optional<User> optionalUser = userRepository.findByEmail(email);

            if (userRepository.findByEmail(email).isEmpty())
                throw new ServiceException("Email não cadastrado!");

            User user = optionalUser.get();
            user.setPassword(PasswordUtils.encryptPassword(novaSenha));

            userRepository.save(user);
        }
        catch (DataIntegrityViolationException e)
        {
            throw new ServiceException("Erro ao redefinir senha! " + e.getMessage());
        }
    }
}
