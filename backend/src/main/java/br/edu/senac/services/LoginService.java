package br.edu.senac.services;

import br.edu.senac.dto.LoginDTO;
import br.edu.senac.entity.ClienteEntity;
import br.edu.senac.entity.LoginEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.repositories.LoginRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.ArrayList;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtEncoder jwtEncoder;

    public LoginEntity insert(@RequestBody @Valid ClienteEntity object, String senha) {
        var loginDTO = new LoginDTO(object.getEmail().toLowerCase().trim(), this.passwordEncoder.encode(senha), object);
        var loginMapper = modelMapper.map(loginDTO, LoginEntity.class);

        return this.loginRepository.save(loginMapper);
    }

    public String login(LoginDTO loginDTO) {
        var cliente = this.loginRepository.findByEmail(loginDTO.getEmail()).orElseThrow(
                () -> new ErrorResponseException(HttpStatus.NOT_FOUND, "Cliente não encontrado.")
        );

        if (!cliente.getClienteEntity().isStatus()) {
            throw new ErrorResponseException(HttpStatus.FORBIDDEN, "O cliente está inativo.");
        }

        if (!passwordEncoder.matches(loginDTO.getSenha(), cliente.getSenha())) {
            throw new ErrorResponseException(HttpStatus.UNAUTHORIZED, "E-mail ou senha incorretos.");
        }

        var issuedAt = Instant.now();
        var expiresAt = 60 * 60 * 2; // 2 horas

        var roles = new ArrayList<String>();

        roles.add("CLIENTE");

        var claims = JwtClaimsSet.builder()
                .issuer("TechCommerce")
                .claim("id", cliente.getId().toString())
                .claim("nome", cliente.getClienteEntity().getNome())
                .claim("email", cliente.getEmail())
                .claim("roles", roles)
                .subject(cliente.getClienteEntity().getId().toString())
                .issuedAt(issuedAt)
                .expiresAt(issuedAt.plusSeconds(expiresAt))
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
