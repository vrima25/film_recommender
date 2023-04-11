package com.enigma.film_recommender.exception;
public class DuplicateException extends RuntimeException{
    public DuplicateException() {
        super("DATA ALREADY EXIST");
    }

    public DuplicateException(String message) {
        super(message);
    }
}
