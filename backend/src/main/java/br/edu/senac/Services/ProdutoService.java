package br.edu.senac.Services;

import br.edu.senac.Entity.ProdutoEntity;
import br.edu.senac.Exceptions.ErrorResponseException;
import br.edu.senac.Pattern.IServicePattern;
import br.edu.senac.Repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService implements IServicePattern<ProdutoEntity, Long> {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    public List<ProdutoEntity> findByCategoriaEntityId(Long id) {
        this.categoriaService.findById(id);
        return this.produtoRepository.findByCategoriaEntityId(id);
    }

    @Override
    public List<ProdutoEntity> findAll() {
        return this.produtoRepository.findAll();
    }

    @Override
    public ProdutoEntity findById(Long id) {
        return this.produtoRepository.findById(id).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Produto não encontrado.")
        );
    }

    @Override
    public ProdutoEntity insert(ProdutoEntity object) {
        return null;
    }

    @Override
    public ProdutoEntity update(Long id, ProdutoEntity object) {
        return null;
    }

    public ProdutoEntity update(Long id, Boolean status) {
        return null;
    }


    @Override
    public void delete(Long id) {

    }
}
