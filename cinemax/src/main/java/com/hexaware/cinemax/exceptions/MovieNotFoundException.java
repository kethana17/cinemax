package com.hexaware.cinemax.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MovieNotFoundException extends ResponseStatusException {

    public MovieNotFoundException(HttpStatus status, String msg) {
        super(status, msg);
    }
}