package br.edu.senac.patterns;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class RepositoryGeneric<T, U> implements IRepositoryGeneric<T, U> {

    private final JpaRepository<T, U> repository;

    public RepositoryGeneric(JpaRepository<T, U> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(U id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public T insert(T object) {
        return repository.save(object);
    }

    @Override
    public T update(U id, T object) {
        return repository.save(object);
    }

    @Override
    public void delete(U id) {
        repository.deleteById(id);
    }
}
