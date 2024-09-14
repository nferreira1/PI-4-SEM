package br.edu.senac.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


public interface IControllerPattern<T> {

      ResponseEntity<T> findAll();
      ResponseEntity<T> findById(int id);
      ResponseEntity<T> Insert(T object);
      ResponseEntity<T> Update(int id,T object);
}
