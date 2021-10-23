package pl.szymonstankowski.email;

public interface EmailSender {

    //TODO MM: email -> content albo body;
    void send(String to, String email);
}
