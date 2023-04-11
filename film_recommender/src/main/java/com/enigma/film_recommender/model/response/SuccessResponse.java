package com.enigma.film_recommender.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class SuccessResponse<T> extends CommonResponse {
    private T data;

    public SuccessResponse(String message, T data) {
        super.setCode("00");
        super.setMessage(message);
        super.setStatus(HttpStatus.OK.name());
        this.data = data;
    }
}
