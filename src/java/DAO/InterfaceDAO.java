package DAO;

import java.util.List;

public interface InterfaceDAO<T, E> {
    public abstract boolean salvar(T objeto);
    public abstract T consultar(E chave);
    public abstract boolean alterar(T objeto);
    public abstract boolean excluir(T objeto);
    public abstract List<T> findAll();
}
