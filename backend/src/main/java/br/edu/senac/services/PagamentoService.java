package br.edu.senac.services;

import br.edu.senac.entity.PagamentoEntity;
import br.edu.senac.interfaces.IPagamento;
import br.edu.senac.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService implements IPagamento {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Override
    public List<PagamentoEntity> findAll() {

        return pagamentoRepository.findAll();
    }

    @Override
    public PagamentoEntity findById(Long id) {
        return null;
    }

    @Override
    public PagamentoEntity insert(PagamentoEntity object) {
        return null;
    }


    @Override
    public PagamentoEntity update(Long id, PagamentoEntity object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

}
