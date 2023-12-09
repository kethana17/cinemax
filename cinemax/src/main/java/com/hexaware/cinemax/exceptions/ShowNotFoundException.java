package com.hexaware.cinemax.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ShowNotFoundException extends ResponseStatusException {

    public ShowNotFoundException(HttpStatus status, String msg) {
        super(status, msg);
    }
}