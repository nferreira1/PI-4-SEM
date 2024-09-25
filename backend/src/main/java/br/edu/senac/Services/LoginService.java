package br.edu.senac.Services;

import br.edu.senac.DTO.ClienteDTO;
import br.edu.senac.DTO.LoginDTO;
import br.edu.senac.Entity.ClienteEntity;
import br.edu.senac.Entity.LoginEntity;
import br.edu.senac.Pattern.IServicePattern;
import br.edu.senac.Repositories.LoginRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class LoginService {

   @Autowired
   private LoginRepository loginRepository;

   @Autowired
   private ModelMapper modelMapper;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginEntity insert(@RequestBody @Valid ClienteEntity object, String senha) {
        LoginDTO loginDTO = new LoginDTO(object.getEmail(),registerLogin(senha), object );

        var loginMapper = modelMapper.map(loginDTO, LoginEntity.class);

        return loginRepository.save(loginMapper);
    }

    public String registerLogin(String plainPassword) {
        return  passwordEncoder.encode(plainPassword);
    }

}
