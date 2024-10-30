package br.edu.senac.patterns;

import java.util.List;

public interface IServiceGeneric<T, U> {

    List<T> findAll();

    T findById(U id);

    T insert(T object);

    T update(U id, T object);

    void delete(U id);
}
