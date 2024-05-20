package br.com.lux.controller.email;

import br.com.lux.services.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lux.services.email.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController
{
    @Autowired
    private final EmailService emailService;

    public EmailController(EmailService emailService)
    {
        this.emailService = emailService;
    }

    @PostMapping
    public void sendEmail(@RequestBody Email email)
    {
        emailService.sendEmail(email);
    }
}
