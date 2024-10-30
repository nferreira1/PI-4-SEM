package br.edu.senac.services;

import br.edu.senac.annotations.ValidateBeforeExecutionAnnotation;
import br.edu.senac.entity.CategoriaEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.interfaces.ICategoria;
import br.edu.senac.patterns.ServiceGeneric;
import br.edu.senac.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService extends ServiceGeneric<CategoriaEntity, Long> implements ICategoria {

    @Autowired
    private CategoriaRepository categoriarepository;

    @Autowired
    private ImageManagerService imageManagerService;

    public CategoriaService(JpaRepository<CategoriaEntity, Long> repository) {
        super(repository);
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
        categoria.setStatus(object.isStatus());
        categoria.setImagem(urlImage);

        return this.categoriarepository.save(categoria);
    }

    @Transactional
    public CategoriaEntity update(Long id) {
        var categoria = this.findById(id);
        categoria.setStatus(!categoria.isStatus());
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
