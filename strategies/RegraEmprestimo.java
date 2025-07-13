package strategies;

import model.Usuario;
import model.livros.Livro;

public interface RegraEmprestimo {
    boolean podeEmprestar(Usuario usuario, Livro livro);
}
