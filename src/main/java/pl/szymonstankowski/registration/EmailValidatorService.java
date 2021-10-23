package pl.szymonstankowski.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Pattern;

//TODO MM: a w jakim celu Ci jest potrzebny serwis? To jest util albo predicate, ale nie jest to na pewno serwis
@Service
public class EmailValidatorService implements Predicate<String> {

    @Override
    public boolean test(String s) {
        //TODO MM: do stałej, najlepiej od razu skompilowany w patternie
        //        Pattern.compile(regex);
        //        Pattern.compile(regex).matcher(s).matches();
        String regex = "^[a-zA-Z0-9_.]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}$";
        //TODO MM: a boolean którego zwaca mataches nie wystawrczy?
        if (s.matches(regex)){
        return true;
        }else {
            return false;
        }
    }
}
