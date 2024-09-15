package br.edu.senac.Pattern;

import java.util.List;

public interface IServicePattern<T, U> {

    List<T> findAll();

    T findById(U id);

    T insert(U id, T object);

    T update(U id, T object);

    void delete(U id);

}
