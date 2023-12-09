package com.hexaware.cinemax.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TheatreNotFoundException extends ResponseStatusException {

    public TheatreNotFoundException(HttpStatus status, String msg) {
        super(status, msg);
    }
}