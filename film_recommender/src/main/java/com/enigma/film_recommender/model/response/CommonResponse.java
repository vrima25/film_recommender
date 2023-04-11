package com.enigma.film_recommender.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class CommonResponse {
    private String code;
    private String status;
    private String message;
}
