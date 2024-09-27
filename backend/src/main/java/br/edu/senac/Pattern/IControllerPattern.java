package br.edu.senac.Pattern;

import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IControllerPattern<T, U> {

    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> getById(U id);

    ResponseEntity<T> post(T object);

    ResponseEntity<T> put(U id, T object);

}
