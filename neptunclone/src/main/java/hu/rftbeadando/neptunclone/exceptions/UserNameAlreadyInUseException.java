package hu.rftbeadando.neptunclone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Username already in use.")
public class UserNameAlreadyInUseException extends RuntimeException{
    public UserNameAlreadyInUseException(String message) {
        super(message);
    }
}
