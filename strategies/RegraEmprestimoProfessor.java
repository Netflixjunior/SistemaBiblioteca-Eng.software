package strategies;

import model.Usuario;
import model.livros.Livro;

public class RegraEmprestimoProfessor implements RegraEmprestimo {
    @Override
    public boolean podeEmprestar(Usuario usuario, Livro livro) {
        // Regras dos professores
        return true;
    }
}
