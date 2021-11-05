package pl.szymonstankowski.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final JavaMailSender mailSender;


    @Override
    public void send(String to, String body) throws MessagingException {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("szymonstankowski1@gmail.com");
        message.setTo(to);
        message.setText(body);
        message.setSubject("Potwierdzenie rejestracji");
        mailSender.send(message);
        System.out.println("Wyslano maila...");

    }

}

