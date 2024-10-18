package br.edu.senac.Services;

import br.edu.senac.Entity.EnderecoEntity;
import br.edu.senac.Pattern.IServicePattern;
import br.edu.senac.Repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService implements IServicePattern<EnderecoEntity, Long> {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public List<EnderecoEntity> findAll() {
        return List.of();
    }

    @Override
    public EnderecoEntity findById(Long id) {
        return null;
    }

    @Override
    public EnderecoEntity insert(EnderecoEntity object) {
        return this.enderecoRepository.save(object);
    }

    @Override
    public EnderecoEntity update(Long id, EnderecoEntity object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
