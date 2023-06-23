package nl.fontys.sem3.individualtrack.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidException extends ResponseStatusException {
    public InvalidException(String errorCode) { super(HttpStatus.BAD_REQUEST, errorCode); }
}
