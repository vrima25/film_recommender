package com.enigma.film_recommender.controller;

import com.enigma.film_recommender.model.Film;
import com.enigma.film_recommender.model.User;
import com.enigma.film_recommender.model.request.FilmRequest;
import com.enigma.film_recommender.model.request.UserRequest;
import com.enigma.film_recommender.model.response.SuccessResponse;
import com.enigma.film_recommender.service.FilmService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/films")
public class FilmController {
    private final FilmService service;

    @Autowired
    private ModelMapper modelMapper;

    public FilmController(FilmService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid FilmRequest filmRequest) throws Exception {
        Film film = modelMapper.map(filmRequest, Film.class);
        film = service.create(film);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<Film>("CREATED", film));
    }

    @GetMapping
    public ResponseEntity getAll() throws Exception {
        List<Film> films = service.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List<Film>>("SUCCESS", films));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) throws Exception {
        Optional<Film> film = service.getById(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new SuccessResponse<Optional<Film>>("FOUNDED", film));
    }
}
