package com.enigma.film_recommender.model.request;

import com.enigma.film_recommender.utils.AgeRating;
import com.enigma.film_recommender.utils.Genre;

import java.util.Date;

public class FilmRequest {
    private String title;
    private Date releaseDate;
    private Genre genre;
    private AgeRating ageRating;
    private Integer goers;
    private Double rating;
}
