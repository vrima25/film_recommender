package com.enigma.film_recommender.model;

import com.enigma.film_recommender.utils.AgeRating;
import com.enigma.film_recommender.utils.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    private Integer id;
    private String title;
    private Date releaseDate;
    private List<Genre> genre;
    private AgeRating ageRating;
    private Integer goers;
    private Double rating;
}
