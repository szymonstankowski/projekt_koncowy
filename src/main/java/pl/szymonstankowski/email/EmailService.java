package pl.szymonstankowski.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Override
    public void send(String to, String email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("szymonstankowski1@gmail.com");
        message.setTo(to);
        message.setText(email);
        message.setSubject("Potwierdzenie rejestracji");

        mailSender.send(message);
        System.out.println("Wyslano maila...");
    }

}

