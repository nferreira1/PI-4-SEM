package br.edu.senac.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

//    @Autowired
//    private LoginRepository loginRepository;

    @Autowired
    private ModelMapper modelMapper;


//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    public LoginEntity insert(@RequestBody @Valid ClienteEntity object, String senha) {
//        LoginDTO loginDTO = new LoginDTO(object.getEmail(),registerLogin(senha), object );
//
//        var loginMapper = modelMapper.map(loginDTO, LoginEntity.class);
//
//        return loginRepository.save(loginMapper);
//    }
//
//    public String registerLogin(String plainPassword) {
//        return  passwordEncoder.encode(plainPassword);
//    }

}
