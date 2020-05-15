package mk.finki.ukim.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class VeggiePizzaException extends RuntimeException {

    public VeggiePizzaException() {
        super("Not all ingredients are veggie!");
    }
}
