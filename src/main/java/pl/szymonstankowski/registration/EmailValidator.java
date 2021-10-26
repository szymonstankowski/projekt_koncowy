package pl.szymonstankowski.registration;

import java.util.function.Predicate;
import java.util.regex.Pattern;


public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
       return Pattern.compile("^[a-zA-Z0-9_.]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}$").matcher(s).matches();

    }
}
