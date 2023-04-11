package com.enigma.film_recommender.repository;

import java.util.List;
import java.util.Optional;

public interface IRepo<T> {
    T create(T params) throws Exception;
    List<T> getAll() throws Exception;
    Optional<T> getById(Integer id) throws Exception;
//    Optional<List<T>> getBy(U key, String value) throws Exception;
    void update(T params, Integer id) throws Exception;
    void delete(Integer id) throws Exception;
//    List<T> addBulk(List<T> list) throws Exception;
}
