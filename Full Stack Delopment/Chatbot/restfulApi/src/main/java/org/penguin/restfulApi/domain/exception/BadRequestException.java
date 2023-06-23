package org.penguin.restfulApi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
    }
}
