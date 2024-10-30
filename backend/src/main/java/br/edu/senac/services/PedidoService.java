package br.edu.senac.services;

import br.edu.senac.entity.PedidoEntity;
import br.edu.senac.interfaces.IPedido;
import br.edu.senac.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService implements IPedido {

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
