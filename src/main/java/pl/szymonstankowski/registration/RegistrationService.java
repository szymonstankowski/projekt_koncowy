package pl.szymonstankowski.registration;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szymonstankowski.email.EmailSender;
import pl.szymonstankowski.registration.token.ConfirmationToken;
import pl.szymonstankowski.registration.token.ConfirmationTokenService;
import pl.szymonstankowski.user.User;
import pl.szymonstankowski.user.UserService;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    private final EmailValidatorService emailValidatorService;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private final static String LINK_CONFIRMATION_EMAIL = "Hello %s! Please click the %s to confirm your registration!";

    public RegistrationService(EmailValidatorService emailValidatorService, UserService userService, ConfirmationTokenService confirmationTokenService, EmailSender emailSender) {
        this.emailValidatorService = emailValidatorService;
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSender = emailSender;
    }

    public String register(User user){
        boolean test = emailValidatorService.test(user.getEmail());
        if (!test){
            throw new IllegalStateException("email not valid");
        }

        String token = userService.saveUser(
                new User(
                        user.getName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole()
                )
        );

        String link = "http://localhost:8080/confirm?token="+token;
        emailSender.send(user.getEmail(), buildEmail(user.getName(), link));
        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(
                confirmationToken.getUser().getEmail());
        return "redirect:/login";
    }

    private String buildEmail(String name, String link) {
        return String.format(LINK_CONFIRMATION_EMAIL, name, link);
    }
}
