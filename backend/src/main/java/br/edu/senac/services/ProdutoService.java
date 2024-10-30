package br.edu.senac.services;

import br.edu.senac.annotations.ValidateBeforeExecutionAnnotation;
import br.edu.senac.entity.ProdutoEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.interfaces.IProduto;
import br.edu.senac.repositories.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProdutoService implements IProduto {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ImageManagerService imageManagerService;

    @Autowired
    private ModelMapper modelMapper;

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
    @Transactional
    public ProdutoEntity insert(ProdutoEntity object) {
        var produtoImagens = object.getImagens();

        object.setImagens(null);

        var produto = this.produtoRepository.save(object);

        produtoImagens.forEach(imagem -> imagem.setProdutoEntity(produto));

        for (byte i = 0; i < produtoImagens.size(); i++) {
            var urlImage = this.imageManagerService.insert(produtoImagens.get(i).getImagem(), "/produtos", produto.getId() + "-" + produto.getNome() + "-" + i).orElseThrow(
                    () -> new ErrorResponseException(HttpStatus.BAD_REQUEST, "Erro ao salvar a imagem.")
            );
            produtoImagens.get(i).setImagem(urlImage);
        }

        produto.setImagens(produtoImagens);

        return this.produtoRepository.save(produto);
    }

    @Override
    @Transactional
    @ValidateBeforeExecutionAnnotation
    public ProdutoEntity update(Long id, ProdutoEntity object) {
        var produto = this.findById(id);

        for (var imagem : new ArrayList<>(produto.getImagens())) {
            this.imageManagerService.delete(imagem.getImagem());
            produto.getImagens().remove(imagem);
        }

        var novasImagens = object.getImagens();

        for (int i = 0; i < novasImagens.size(); i++) {
            var novaImagem = novasImagens.get(i);
            String urlImage = this.imageManagerService.insert(novaImagem.getImagem(), "/produtos", produto.getId() + "-" + produto.getNome() + "-" + i).orElseThrow(
                    () -> new ErrorResponseException(HttpStatus.BAD_REQUEST, "Erro ao salvar a imagem.")
            );
            novaImagem.setImagem(urlImage);
            novaImagem.setProdutoEntity(produto);
            produto.getImagens().add(novaImagem);
        }

        produto.setNome(object.getNome());
        produto.setDescricao(object.getDescricao());
        produto.setEstoque(object.getEstoque());
        produto.setValor(object.getValor());
        produto.setStatus(object.isStatus());
        produto.setCategoriaEntity(object.getCategoriaEntity());

        return this.produtoRepository.save(produto);
    }

    @Transactional
    public ProdutoEntity update(Long id) {
        var produto = this.findById(id);
        produto.setStatus(!produto.isStatus());
        return this.produtoRepository.save(produto);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var produto = this.findById(id);
        this.produtoRepository.delete(produto);
        produto.getImagens().forEach(imagem -> this.imageManagerService.delete(imagem.getImagem()));
    }
}
