package strategies;

import model.Usuario;
import model.livros.Exemplar;
import model.livros.Livro;

public class RegraEmprestimoProfessor implements RegraEmprestimo {
    @Override
    public boolean podeEmprestar(Usuario usuario, Livro livro) {
        return livro.getExemplares().stream()
                .anyMatch(Exemplar::isDisponivel);
    }
}

