package commands;

import model.Usuario;
import model.livros.Exemplar;
import model.livros.Livro;
import model.transacoes.Emprestimo;
import model.transacoes.Reserva;
import repository.Repositorio;
import utils.Busca;

import java.time.LocalDate;

public class EmprestimoCommand implements Comando {

    @Override
    public void executar(String[] args) {

        if (args.length < 3) {
            System.out.println("Modo de Uso: emp <codigoUsuario> <codigoLivro>");
            return;
        }

        int codUsuario = Integer.parseInt(args[1]);
        int codLivro = Integer.parseInt(args[2]);


        //__________________________________________________________________________
        Usuario usuario = Busca.buscarUsuario(codUsuario);
        if (usuario == null) {return; }

        Livro livro = Busca.buscarLivro(codLivro);
        if (livro == null) {return;}
        //__________________________________________________________________________

        // verifica regra de empréstimo
        boolean pode = usuario.getRegraEmprestimo().podeEmprestar(usuario, livro);
        if (!pode) {
            System.out.println("Não foi possível realizar o empréstimo. Verifique as regras.");
            return;
        }

        // pega um exemplar disponível."
        Exemplar exemplarDisponivel = livro.getExemplares().stream()
                .filter(Exemplar::isDisponivel)
                .findFirst()
                .orElse(null);

        if (exemplarDisponivel == null) {
            System.out.println("Não há exemplares disponíveis.");
            return;
        }

        // remove a reserva do usuário.
        livro.getReservas().removeIf(reserva -> reserva.getUsuario().equals(usuario));
        usuario.getReservas().removeIf(reserva -> reserva.getLivro().equals(livro));

        // marca exemplar como não disponível
        exemplarDisponivel.setDisponivel(false);

        // prazo de devolução
        int diasEmprestimo = usuario.getPrazoDevolucao();
        LocalDate hoje = LocalDate.now();
        LocalDate devolucao = hoje.plusDays(diasEmprestimo);

        // Cria o empréstimo
        Emprestimo emprestimo = new Emprestimo(hoje, devolucao);
        emprestimo.setUsuario(usuario);
        emprestimo.setExemplar(exemplarDisponivel);

        usuario.getEmprestimos().add(emprestimo);

        System.out.println("Empréstimo realizado com sucesso! Data de devolução: " + devolucao);
    }
}
