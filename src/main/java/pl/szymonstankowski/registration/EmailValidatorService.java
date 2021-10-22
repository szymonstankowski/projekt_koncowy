package pl.szymonstankowski.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidatorService implements Predicate<String> {

    @Override
    public boolean test(String s) {
        String regex = "^[a-zA-Z0-9_.]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}$";
        if (s.matches(regex)){
        return true;
        }else {
            return false;
        }
    }
}
