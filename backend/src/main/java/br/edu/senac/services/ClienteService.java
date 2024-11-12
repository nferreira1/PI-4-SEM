package br.edu.senac.services;

import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.ClienteEntity;
import br.edu.senac.patterns.ServiceGeneric;
import br.edu.senac.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService extends ServiceGeneric<ClienteEntity, Long> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private EnderecoService enderecoService;

    public ClienteService(JpaRepository<ClienteEntity, Long> repository) {
        super(repository);
    }

    @Override
    public ClienteEntity insert(ClienteEntity object) {
        var cliente = this.clienteRepository.save(object);
        var carrinho = new CarrinhoEntity();
        carrinho.setClienteEntity(cliente);
        cliente.setCarrinhoEntity(carrinho);

        this.carrinhoService.insert(carrinho);

        if (!object.getEnderecoEntities().isEmpty()) {
            object.getEnderecoEntities().forEach(endereco -> {
                endereco.setClienteEntity(cliente);
                this.enderecoService.insert(endereco);
            });
        }

        return cliente;
    }

    @Transactional
    public ClienteEntity update(Long id) {
        var cliente = this.findById(id);
        cliente.setStatus(!cliente.isStatus());
        return cliente;
    }

}
