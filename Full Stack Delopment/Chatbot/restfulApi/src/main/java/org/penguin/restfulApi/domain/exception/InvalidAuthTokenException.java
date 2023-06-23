package org.penguin.restfulApi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidAuthTokenException extends ResponseStatusException {
    public InvalidAuthTokenException() {
        super(HttpStatus.BAD_REQUEST, "INVALID_AUTH_TOKEN");
    }
}
