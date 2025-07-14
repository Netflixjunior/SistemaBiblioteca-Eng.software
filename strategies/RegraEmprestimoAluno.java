package strategies;

import model.Usuario;
import model.livros.Exemplar;
import model.livros.Livro;
import model.transacoes.Emprestimo;
import model.transacoes.Reserva;

import java.time.LocalDate;

public class RegraEmprestimoAluno implements RegraEmprestimo {

    @Override
    public boolean podeEmprestar(Usuario usuario, Livro livro) {
        // 1. Verifica se há exemplar disponível
        boolean temDisponivel = livro.getExemplares().stream()
                .anyMatch(Exemplar::isDisponivel);
        if (!temDisponivel) {
            return false;
        }

        // 2. Verifica se o usuário tem empréstimos em atraso
        boolean temAtraso = usuario.getEmprestimos().stream()
                .anyMatch(e -> !e.isFinalizado() &&
                        e.getDataPrevistaDevolucao().isBefore(LocalDate.now()));
        if (temAtraso) {
            return false;
        }

        // 3. Verifica limite de empréstimos
        int limite = usuario.getLimiteEmprestimos();
        long ativos = usuario.getEmprestimos().stream()
                .filter(e -> !e.isFinalizado())
                .count();
        if (ativos >= limite) {
            return false;
        }

        // 4 e 5. Reservas em relação aos exemplares disponíveis
        long reservas = livro.getReservas().size();
        long disponiveis = livro.getExemplares().stream()
                .filter(Exemplar::isDisponivel)
                .count();

        boolean usuarioReservou = livro.getReservas().stream()
                .anyMatch(r -> r.getUsuario().equals(usuario));

        if (reservas >= disponiveis && !usuarioReservou) {
            return false;
        }

        // 6. Já possui empréstimo do mesmo livro
        boolean jaTemLivro = usuario.getEmprestimos().stream()
                .filter(e -> !e.isFinalizado())
                .anyMatch(e -> e.getExemplar().getLivro().equals(livro));
        if (jaTemLivro) {
            return false;
        }

        return true;
    }
}
