package br.edu.senac.Pattern;

import br.edu.senac.Entity.CategoriaEntity;

import java.util.List;

public interface IServicePattern<T, U>
{

    List<T> GetAll();
    T GetId(U id);
    T Post(U id, T object);
    T Update(U id, T object);
    void  Delete(U id);
}
