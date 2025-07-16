package strategies;

import model.Usuario;
import model.livros.Exemplar;
import model.livros.Livro;

import java.time.LocalDate;

public class RegraEmprestimoAluno implements RegraEmprestimo {

    @Override
    public boolean podeEmprestar(Usuario usuario, Livro livro) {
        // Regra 1
        boolean temDisponivel = livro.getExemplares().stream()
                .anyMatch(Exemplar::isDisponivel);
        if (!temDisponivel) {
            return false;
        }

        // Regra 2
        boolean temAtraso = usuario.getEmprestimos().stream()
                .anyMatch(e -> !e.isFinalizado() &&
                        e.getDataPrevistaDevolucao().isBefore(LocalDate.now()));
        if (temAtraso) {
            return false;
        }

        // Regra 3
        int limite = usuario.getLimiteEmprestimos();
        long ativos = usuario.getEmprestimos().stream()
                .filter(e -> !e.isFinalizado())
                .count();
        if (ativos >= limite) {
            return false;
        }

        // Regras 4 e 5
        long reservas = livro.getReservas().size();
        long disponiveis = livro.getExemplares().stream()
                .filter(Exemplar::isDisponivel)
                .count();

        boolean usuarioReservou = livro.getReservas().stream()
                .anyMatch(r -> r.getUsuario().equals(usuario));

        if (reservas >= disponiveis && !usuarioReservou) {
            return false;
        }

        // Regra 6
        boolean jaTemLivro = usuario.getEmprestimos().stream()
                .filter(e -> !e.isFinalizado())
                .anyMatch(e -> e.getExemplar().getLivro().equals(livro));
        if (jaTemLivro) {
            return false;
        }

        return true;
    }
}
