package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SpicyIngredientException extends RuntimeException {

    public SpicyIngredientException() {
        super("There can be max 3 spicy ingredients in the menu!");
    }
}
