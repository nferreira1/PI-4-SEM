package br.edu.senac.Pattern;

import br.edu.senac.Entity.CategoriaEntity;

import java.util.List;

public interface IServicePattern<T>
{
    List<T> GetAll();
    T GetId(Long id);
    T Post(Long id, T object);
    T Update(Long id, T object);
    void  Delete(Long id);
}
