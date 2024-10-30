package br.edu.senac.services;

import br.edu.senac.entity.EnderecoEntity;
import br.edu.senac.interfaces.IEndereco;
import br.edu.senac.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService implements IEndereco {

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
