package br.edu.senac.config;

import br.edu.senac.dto.CarrinhoProdutosDTO;
import br.edu.senac.dto.CarrinhoProdutosResponseDTO;
import br.edu.senac.dto.ClienteDTO;
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
                });

        // Mapeamento de CarrinhoProdutosEntity para CarrinhoProdutosDTO
        modelMapper.typeMap(CarrinhoProdutosEntity.class, CarrinhoProdutosDTO.class)
                .addMappings(mapper -> {
                    mapper.map(CarrinhoProdutosEntity::getId, CarrinhoProdutosDTO::setId);
                    mapper.map(src -> src.getCarrinho().getClienteEntity().getId(), CarrinhoProdutosDTO::setClienteId);
                    mapper.map(src -> src.getProduto().getId(), CarrinhoProdutosDTO::setProdutoId);
                    mapper.map(CarrinhoProdutosEntity::getQuantidade, CarrinhoProdutosDTO::setQuantidade);
                });

        // Mapeamento de CarrinhoProdutosEntity para CarrinhoProdutosResponseDTO
        modelMapper.typeMap(CarrinhoProdutosEntity.class, CarrinhoProdutosResponseDTO.class)
                .addMappings(mapper -> {
                    mapper.map(CarrinhoProdutosEntity::getId, CarrinhoProdutosResponseDTO::setId);
                    mapper.map(CarrinhoProdutosEntity::getProduto, CarrinhoProdutosResponseDTO::setProduto);
                    mapper.map(CarrinhoProdutosEntity::getQuantidade, CarrinhoProdutosResponseDTO::setQuantidade);
                });

        return modelMapper;
    }
}


