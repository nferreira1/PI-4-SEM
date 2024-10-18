package br.edu.senac.Services;

import br.edu.senac.Annotations.ValidateBeforeExecutionAnnotation;
import br.edu.senac.Entity.CategoriaEntity;
import br.edu.senac.Exceptions.ErrorResponseException;
import br.edu.senac.Pattern.IServicePattern;
import br.edu.senac.Repositories.CategoriaRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService implements IServicePattern<CategoriaEntity, Long> {

    @Autowired
    private CategoriaRepository categoriarepository;

    @Autowired
    private ImageManagerService imageManagerService;

    @Autowired
    private Validator validator;

    public List<CategoriaEntity> findAll() {
        return this.categoriarepository.findAllByStatusTrue();
    }

    @Override
    public CategoriaEntity findById(Long id) {
        return this.categoriarepository.findById(id).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));
    }

    @Override
    @Transactional
    public CategoriaEntity insert(CategoriaEntity object) {
        var categoria = this.categoriarepository.save(object);
        var urlImage = this.imageManagerService.insert(object.getImagem(), "/categorias", categoria.getId() + "-" + categoria.getNome()).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.BAD_REQUEST, "Erro ao salvar a imagem.")
        );
        categoria.setImagem(urlImage);
        return categoria;
    }

    @Override
    @Transactional
    @ValidateBeforeExecutionAnnotation
    public CategoriaEntity update(Long id, CategoriaEntity object) {
        var categoria = this.findById(id);

        var urlImage = this.imageManagerService.update(categoria.getImagem(), object.getImagem(), categoria.getId() + "-" + object.getNome()).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.BAD_REQUEST, "Erro ao salvar a imagem.")
        );

        categoria.setNome(object.getNome());
        categoria.setStatus(object.getStatus());
        categoria.setImagem(urlImage);

        return this.categoriarepository.save(categoria);
    }

    @Transactional
    public CategoriaEntity update(Long id) {
        var categoria = this.findById(id);
        categoria.setStatus(!categoria.getStatus());
        return this.categoriarepository.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var categoria = this.findById(id);
        this.categoriarepository.delete(categoria);
        this.imageManagerService.delete(categoria.getImagem());
    }
}
