package commands;

import model.livros.Exemplar;
import model.livros.Livro;
import model.transacoes.Emprestimo;
import model.transacoes.Reserva;
import repository.Repositorio;
import utils.Busca;

import java.time.format.DateTimeFormatter;

public class ConsultaLivroCommand implements Comando {
    @Override
    public void executar(String[] args) {
        if (args.length < 2) {
            System.out.println("Modo de Uso: liv <codigoLivro>");
            return;
        }

        int codLivro = Integer.parseInt(args[1]);

        Livro livro = Busca.buscarLivro(codLivro);
        if (livro == null) return;

        // exibe título
        System.out.println("Título: " + livro.getTitulo());

        // exibe reservas
        int totalReservas = livro.getReservas().size();
        System.out.println("Reservas: " + totalReservas);

        if (totalReservas > 0) {
            for (Reserva r : livro.getReservas()) {
                System.out.println("   - " + r.getUsuario().getNome());
            }
        }

        // exibe cada exemplar
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Exemplar ex : livro.getExemplares()) {
            System.out.print("Exemplar " + String.format("%02d", ex.getCodigo()) + " - ");

            if (ex.isDisponivel()) {
                System.out.println("Disponível");
            } else {
                // procura empréstimo do exemplar
                Emprestimo emp = Repositorio.getInstance().getUsuarios().stream()
                        .flatMap(u -> u.getEmprestimos().stream())
                        .filter(e -> !e.isFinalizado() && e.getExemplar().equals(ex))
                        .findFirst()
                        .orElse(null);

                    System.out.println("Emprestado para " + emp.getUsuario().getNome() +
                            " | Empréstimo: " + emp.getDataEmprestimo().format(fmt) +
                            " | Devolução prevista: " + emp.getDataPrevistaDevolucao().format(fmt));

            }
        }
    }
}

