package br.edu.senac.services;

import br.edu.senac.dto.LoginDTO;
import br.edu.senac.entity.ClienteEntity;
import br.edu.senac.entity.LoginEntity;
import br.edu.senac.exceptions.ErrorResponseException;
import br.edu.senac.interfaces.ILogin;
import br.edu.senac.repositories.LoginRepository;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.And;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class LoginService implements ILogin {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ModelMapper modelMapper;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginEntity insert(@RequestBody @Valid ClienteEntity object, String senha) {

        LoginDTO loginDTO = new LoginDTO(object.getEmail().toLowerCase().trim(), registerLogin(senha.toLowerCase().trim()), object);

        var loginMapper = modelMapper.map(loginDTO, LoginEntity.class);

        return loginRepository.save(loginMapper);
    }

    public LoginDTO fazendoLogin(LoginDTO loginDTO) {

        LoginEntity login = modelMapper.map(loginDTO, LoginEntity.class);

        //Fazer nathan

        if( (login == null) || (login.getEmail() != loginDTO.getEmail().toLowerCase().trim() || login.getSenha() != loginDTO.getSenha().toLowerCase().trim()) )
        {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST, "O e-mail ou a senha estão incorretos");
        }

        var loginsDTO = modelMapper.map(login, LoginDTO.class);

        return loginsDTO;
    }

    private String registerLogin(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

}
