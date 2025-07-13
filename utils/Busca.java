package utils;

import model.Usuario;
import model.livros.Livro;
import repository.Repositorio;

//Busca Usuário e livro informada, se não forem encontrados imprime "Usuário/Livro não encontrado"
public class Busca {

    public static Usuario buscarUsuario(int codigo) {
        Usuario usuario = Repositorio.getInstance().getUsuarios().stream()
                .filter(u -> u.getCodigo() == codigo)
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
        }

        return usuario;
    }

    public static Livro buscarLivro(int codigo) {
        Livro livro = Repositorio.getInstance().getLivros().stream()
                .filter(l -> l.getCodigo() == codigo)
                .findFirst()
                .orElse(null);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
        }

        return livro;
    }
}

