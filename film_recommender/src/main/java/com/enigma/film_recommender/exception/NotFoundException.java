package com.enigma.film_recommender.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("DATA NOT FOUND");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
