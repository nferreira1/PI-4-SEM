package br.edu.senac.Services;

import br.edu.senac.DTO.LoginDTO;
import br.edu.senac.Entity.ClienteEntity;
import br.edu.senac.Entity.LoginEntity;
import br.edu.senac.Interfaces.ILogin;
import br.edu.senac.Repositories.LoginRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class LoginService  implements ILogin {

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
