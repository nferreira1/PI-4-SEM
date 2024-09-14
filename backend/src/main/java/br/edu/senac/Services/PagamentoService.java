package br.edu.senac.Services;

import br.edu.senac.Entity.CategoriaEntity;
import br.edu.senac.Entity.PagamentoEntity;
import br.edu.senac.Pattern.IServicePattern;
import br.edu.senac.Repositories.CategoriaRepository;
import br.edu.senac.Repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService implements IServicePattern<PagamentoEntity> {

    @Autowired
    private PagamentoRepository pagamentoRepository;//injeção de dependencia


    @Override
    public List<PagamentoEntity> GetAll() {
        return pagamentoRepository.findAll();
    }

    @Override
    public PagamentoEntity GetId(Long id) {
        return null;
    }

    @Override
    public PagamentoEntity Post(Long id, PagamentoEntity object) {
        return null;
    }

    @Override
    public PagamentoEntity Update(Long id, PagamentoEntity object) {
        return null;
    }

    @Override
    public void Delete(Long id) {

    }
}
