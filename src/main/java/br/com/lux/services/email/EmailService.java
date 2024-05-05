package br.com.lux.services.email;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EmailService
{
    @Getter
    private static EmailService instance;

    @Autowired
    private JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        instance = this;
    }

    public void sendEmail(Email email)
    {
        try
        {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@lux.com.br");
            message.setTo(email.to());
            message.setSubject(email.subject());
            message.setText(email.body());
            mailSender.send(message);

            System.out.println("Email enviado com sucesso!");
        }
        catch (Exception e)
        {
            System.out.println("Erro ao enviar email: " + e.getMessage());
        }
    }
}
