package br.edu.senac.Services;

import br.edu.senac.Entity.CategoriaEntity;
import br.edu.senac.Repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository Categoriarepository;//injeção de dependencia

    public List<CategoriaEntity> findAll(){ //criou a função que retorna todas as categorias

        return this.Categoriarepository.findAll();
    }

}
