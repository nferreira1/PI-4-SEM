package br.edu.senac.patterns;

import br.edu.senac.exceptions.ErrorResponseException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class ServiceGeneric<T, U> implements IServiceGeneric<T, U> {

    private final JpaRepository<T, U> repository;

    public ServiceGeneric(JpaRepository<T, U> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        return this.repository.findAll();
    }

    @Override
    public T findById(U id) {
        String entityName = this.getClass().getSimpleName().replace("Service", "");
        String message = entityName + " não encontrado.";
        return this.repository.findById(id).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND, message)
        );
    }

    @Override
    public T insert(T object) {
        return this.repository.save(object);
    }

    @Override
    public T update(U id, T object) {
        this.findById(id);
        return this.repository.save(object);
    }

    @Override
    public void delete(U id) {
        this.findById(id);
        this.repository.deleteById(id);
    }
}
