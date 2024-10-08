package br.edu.senac.Services;

import br.edu.senac.Entity.CategoriaEntity;
import br.edu.senac.Entity.PedidoEntity;
import br.edu.senac.Exceptions.ErrorResponseException;
import br.edu.senac.Pattern.IServicePattern;
import br.edu.senac.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService implements IServicePattern<PedidoEntity, Long> {

    @Autowired
    private CategoriaRepository categoriarepository;


    @Override
    public List<PedidoEntity> findAll() {
        return List.of();
    }

    @Override
    public PedidoEntity findById(Long id) {
        return null;
    }

    @Override
    public PedidoEntity insert(PedidoEntity object) {
        return null;
    }

    @Override
    public PedidoEntity update(Long id, PedidoEntity object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
