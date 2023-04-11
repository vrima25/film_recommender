package com.enigma.film_recommender.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private Integer id;
    private User user;
    private Film film;
    private Double rating;
}
