package br.edu.senac.services;

import br.edu.senac.models.Categoria;
import br.edu.senac.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository Categoriarepository;//injeção de dependencia

    public List<Categoria> findAll(){ //criou a função que retorna todas as categorias

        return this.Categoriarepository.findAll();
    }

}
