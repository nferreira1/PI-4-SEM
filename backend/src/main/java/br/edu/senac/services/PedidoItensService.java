package br.edu.senac.services;

import br.edu.senac.entity.PedidoItensEntity;
import br.edu.senac.interfaces.IPedidoItem;
import br.edu.senac.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoItensService implements IPedidoItem {

    @Autowired
    private CategoriaRepository categoriarepository;

    @Override
    public List<PedidoItensEntity> findAll() {
        return List.of();
    }

    @Override
    public PedidoItensEntity findById(Long id) {
        return null;
    }

    @Override
    public PedidoItensEntity insert(PedidoItensEntity object) {
        return null;
    }

    @Override
    public PedidoItensEntity update(Long id, PedidoItensEntity object) {

        //Esse cara vai ser mais rigído
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
