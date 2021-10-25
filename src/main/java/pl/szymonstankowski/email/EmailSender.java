package pl.szymonstankowski.email;

import javax.mail.MessagingException;

public interface EmailSender {

    void send(String to, String body) throws MessagingException;
}
