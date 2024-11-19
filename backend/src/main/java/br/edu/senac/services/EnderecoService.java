package br.edu.senac.services;

import br.edu.senac.entity.EnderecoEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.patterns.ServiceGeneric;
import br.edu.senac.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EnderecoService extends ServiceGeneric<EnderecoEntity, Long> {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoService(JpaRepository<EnderecoEntity, Long> repository) {
        super(repository);
    }

    @Override
    public EnderecoEntity insert(EnderecoEntity object) {

        if (object.getClienteEntity().getEnderecos().size() == 3) {
            throw new ErrorResponseException(HttpStatus.UNPROCESSABLE_ENTITY, "Só é possível cadastrar até 3 endereços.");
        }

        object.getClienteEntity().getEnderecos().forEach(e -> {
            e.setEnderecoPrincipal(false);
            super.update(e.getId(), e);
        });

        if (object.isEnderecoPrincipal()) {
            object.setEnderecoPrincipal(true);
        }

        return super.insert(object);
    }

    @Override
    public EnderecoEntity update(Long id, EnderecoEntity object) {
        var endereco = super.findById(id);

        if (!Objects.equals(object.getClienteEntity().getId(), endereco.getClienteEntity().getId())) {
            throw new ErrorResponseException(HttpStatus.NOT_FOUND, "Endereço não encontrado");
        }

        if (endereco.isEnderecoPrincipal() && !object.isEnderecoPrincipal()) {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST, "É necessário ter ao menos um endereço como principal.");
        }

        endereco.getClienteEntity().getEnderecos().forEach(e -> {
            e.setEnderecoPrincipal(false);
            super.update(e.getId(), e);
        });

        if (object.isEnderecoPrincipal()) {
            endereco.setEnderecoPrincipal(true);
        }

        return super.update(id, object);
    }

    public EnderecoEntity update(Long id) {
        var endereco = super.findById(id);

        endereco.getClienteEntity().getEnderecos().forEach(e -> {
            e.setEnderecoPrincipal(false);
            super.update(e.getId(), e);
        });

        endereco.setEnderecoPrincipal(true);

        return super.update(id, endereco);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        var endereco = super.findById(id);

        if (endereco.isEnderecoPrincipal()) {
            throw new ErrorResponseException(HttpStatus.CONFLICT, "Não é possível excluir um endereço principal.");
        }

        super.delete(id);
    }

}
