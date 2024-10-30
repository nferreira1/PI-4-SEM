package br.edu.senac.services;

import br.edu.senac.entity.ProdutoImagensEntity;
import br.edu.senac.patterns.IServiceGeneric;
import br.edu.senac.repositories.ProdutoImagensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoImagensService implements IServiceGeneric<ProdutoImagensEntity, Long> {

    // Resolver
    @Autowired
    ProdutoImagensRepository produtoImagensRepository;

    @Override
    public List<ProdutoImagensEntity> findAll() {
        return List.of();
    }

    @Override
    public ProdutoImagensEntity findById(Long id) {
        return null;
    }

    @Override
    @Transactional
    public ProdutoImagensEntity insert(ProdutoImagensEntity object) {
        return null;
    }

    public List<ProdutoImagensEntity> insertList(List<ProdutoImagensEntity> object) {
        return this.produtoImagensRepository.saveAll(object);
    }

    @Override
    public ProdutoImagensEntity update(Long id, ProdutoImagensEntity object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

}
