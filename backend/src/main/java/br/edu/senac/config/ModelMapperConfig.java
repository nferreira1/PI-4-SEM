package br.edu.senac.config;

import br.edu.senac.dto.ClienteDTO;
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

        Converter<Long, GeneroEntity> generoConverter = context -> {
            Long generoId = context.getSource();
            if (generoId == null) {
                return null;
            }
            GeneroEntity genero = new GeneroEntity();
            genero.setId(generoId);
            return genero;
        };

        modelMapper.typeMap(ClienteDTO.class, ClienteEntity.class)
                .addMappings(mapper -> mapper.using(generoConverter)
                        .map(ClienteDTO::getGeneroId, ClienteEntity::setGeneroEntity));

        return modelMapper;
    }
}
