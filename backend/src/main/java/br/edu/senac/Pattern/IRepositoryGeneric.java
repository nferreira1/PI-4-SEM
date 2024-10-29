package br.edu.senac.Pattern;

import java.util.List;

public interface IRepositoryGeneric<T, U>  {

    List<T> findAll();

    T findById(U id);

    T insert(T object);

    T update(U id, T object);

    void delete(U id);
}
