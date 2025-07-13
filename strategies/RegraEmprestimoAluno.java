package strategies;

import model.Usuario;
import model.livros.Livro;

public class RegraEmprestimoAluno implements RegraEmprestimo {
    @Override
    public boolean podeEmprestar(Usuario usuario, Livro livro) {
        // Regras dos alunos
        return true;
    }
}
