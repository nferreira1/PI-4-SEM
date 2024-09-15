package br.edu.senac.Services;

import br.edu.senac.Entity.CategoriaEntity;
import br.edu.senac.Exceptions.ErrorResponseException;
import br.edu.senac.Pattern.IServicePattern;
import br.edu.senac.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements IServicePattern<CategoriaEntity, Long> {

    @Autowired
    private CategoriaRepository categoriarepository;

    public List<CategoriaEntity> findAll() {
        return this.categoriarepository.findAll();
    }

    @Override
    public CategoriaEntity findById(Long id) {
        return this.categoriarepository.findById(id).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));
    }

    @Override
    public CategoriaEntity insert(Long id, CategoriaEntity object) {
        return null;
    }

    @Override
    public CategoriaEntity update(Long id, CategoriaEntity object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
