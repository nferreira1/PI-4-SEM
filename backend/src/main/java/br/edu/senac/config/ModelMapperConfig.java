package br.edu.senac.config;

import br.edu.senac.dto.CarrinhoDTO;
import br.edu.senac.dto.CarrinhoProdutosRequestDTO;
import br.edu.senac.dto.ClienteDTO;
import br.edu.senac.entity.CarrinhoEntity;
import br.edu.senac.entity.CarrinhoProdutosEntity;
import br.edu.senac.entity.ClienteEntity;
import br.edu.senac.entity.GeneroEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Ignora campos nulos
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        // Conversor para GeneroEntity
        Converter<Long, GeneroEntity> generoConverter = context -> {
            Long generoId = context.getSource();
            if (generoId == null) {
                return null;
            }
            GeneroEntity genero = new GeneroEntity();
            genero.setId(generoId);
            return genero;
        };

        // Conversor para transformar emails em letras minúsculas
        Converter<String, String> emailToLowerConverter = context -> {
            String email = context.getSource();
            return email != null ? email.toLowerCase() : null;
        };

        // Mapeamento entre ClienteDTO e ClienteEntity
        modelMapper.typeMap(ClienteDTO.class, ClienteEntity.class)
                .addMappings(mapper -> {
                    mapper.using(generoConverter).map(ClienteDTO::getGeneroId, ClienteEntity::setGeneroEntity);
                    mapper.using(emailToLowerConverter).map(ClienteDTO::getEmail, ClienteEntity::setEmail);
                    mapper.skip(ClienteEntity::setId);
                });

        // Mapeamento de CarrinhoProdutosEntity para CarrinhoProdutosDTO
        modelMapper.typeMap(CarrinhoProdutosEntity.class, CarrinhoProdutosRequestDTO.class)
                .addMappings(mapper -> {
                    mapper.map(CarrinhoProdutosEntity::getId, CarrinhoProdutosRequestDTO::setId);
                    mapper.map(src -> src.getProduto().getId(), CarrinhoProdutosRequestDTO::setProdutoId);
                    mapper.map(CarrinhoProdutosEntity::getQuantidade, CarrinhoProdutosRequestDTO::setQuantidade);
                });

        // Mapeamento de CarrinhoEntity para CarrinhoDTO
        modelMapper.typeMap(CarrinhoEntity.class, CarrinhoDTO.class)
                .addMappings(mapper -> {
                    mapper.map(CarrinhoEntity::getClienteEntity, CarrinhoDTO::setCliente);
                    mapper.map(CarrinhoEntity::getItens, CarrinhoDTO::setItens);
                });


        return modelMapper;
    }
}
