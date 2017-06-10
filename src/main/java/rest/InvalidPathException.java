package org.ca.challenge.nasabot.jeanschmidt.rest;

import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPathException extends RuntimeException {
    public InvalidPathException(String reason) {
        super("Invalid Path: " + reason);
    }
}
