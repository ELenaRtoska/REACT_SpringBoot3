package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IngredientNameException extends RuntimeException {

    public IngredientNameException(String name) {
        super("An ingredient with the name: " + name + " exists!");
    }
}
