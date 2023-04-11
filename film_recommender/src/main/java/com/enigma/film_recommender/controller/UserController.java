package com.enigma.film_recommender.controller;

import com.enigma.film_recommender.model.User;
import com.enigma.film_recommender.model.request.UserRequest;
import com.enigma.film_recommender.model.response.SuccessResponse;
import com.enigma.film_recommender.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @Autowired
    private ModelMapper modelMapper;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid UserRequest userRequest){
        User user = modelMapper.map(userRequest, User.class);
        user = service.create(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse<User>("CREATED", user));
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<User> users = service.getAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new SuccessResponse<List<User>>("SUCCESS", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id){
        Optional<User> user = service.getById(id);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(new SuccessResponse<Optional<User>>("FOUNDED", user));
    }
}
