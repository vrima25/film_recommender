package com.enigma.film_recommender.service;

import com.enigma.film_recommender.exception.DuplicateException;
import com.enigma.film_recommender.exception.NotFoundException;
import com.enigma.film_recommender.model.Film;
import com.enigma.film_recommender.model.User;
import com.enigma.film_recommender.repository.FilmRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService implements IService<Film> {
    private final FilmRepo repo;

    public FilmService(FilmRepo repo) {
        this.repo = repo;
    }


    @Override
    public Film create(Film film) throws Exception {
        try{
            List<Film> films = repo.getAll();
            for(Film find : films){
                if(find.getTitle().equals(film.getTitle())){
                    throw new DuplicateException("FILM ALREADY EXIST");
                }
            }
            return repo.create(film);
        } catch (DuplicateException e){
            throw new DuplicateException(e.getMessage());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Film> getAll() throws Exception {
        try{
            return repo.getAll();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Film> getById(Integer id) throws Exception {
        try{
            Optional<Film> find = repo.getById(id);
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
    public void update(Film params, Integer id) throws Exception {

    }

    @Override
    public void delete(Integer id) throws Exception {

    }
}
