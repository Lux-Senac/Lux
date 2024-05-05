package br.com.lux.services.email;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    private final EmailService emailService;

    public EmailNotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 7000)
    public void checkStateChanges()
    {
        Email email = new Email("destinatario@email.com", "Assunto do email", "Corpo do email");
        emailService.sendEmail(email);
    }
}
