package br.edu.senac.Services;

import br.edu.senac.Entity.ProdutoEntity;
import br.edu.senac.Repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoEntity> findAll() {
        return produtoRepository.findAll();
    }
}
