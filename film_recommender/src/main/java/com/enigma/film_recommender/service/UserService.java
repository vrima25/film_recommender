package com.enigma.film_recommender.service;

import com.enigma.film_recommender.exception.DuplicateException;
import com.enigma.film_recommender.exception.NotFoundException;
import com.enigma.film_recommender.model.User;
import com.enigma.film_recommender.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User> {

    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User create(User user) {
        try{
            List<User> users = repo.getAll();
            for(User find : users){
                if(find.getEmail().equals(user.getEmail())){
                    throw new DuplicateException("EMAIL WAS REGISTERED");
                }
            }
            return repo.create(user);
        } catch (DuplicateException e){
            throw new DuplicateException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        try{
            return repo.getAll();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getById(Integer id) {
        try{
            Optional<User> find = repo.getById(id);
            if(find.isEmpty()){
                throw new NotFoundException();
            }
            return find;
        } catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User params, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }
}
