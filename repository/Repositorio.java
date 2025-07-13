package repository;

import model.Usuario;
import model.livros.Livro;

import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    private static Repositorio instance;
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();

    private Repositorio() {}

    public static Repositorio getInstance() {
        if (instance == null) {
            instance = new Repositorio();
        }
        return instance;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Livro> getLivros() {
        return livros;
    }
}
