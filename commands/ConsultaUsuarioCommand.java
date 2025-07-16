package commands;

import model.Usuario;
import model.livros.Livro;
import model.transacoes.Emprestimo;
import model.transacoes.Reserva;
import utils.Busca;

import java.time.format.DateTimeFormatter;

public class ConsultaUsuarioCommand implements Comando {
    @Override
    public void executar(String[] args) {
        if (args.length < 2) {
            System.out.println("Modo de Uso: usu <codigoUsuario>");
            return;
        }

        int codUsuario = Integer.parseInt(args[1]);

        Usuario usuario = Busca.buscarUsuario(codUsuario);
        if (usuario == null) return;

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Usuário: " + usuario.getNome());

        //emprestimos
        System.out.println("Empréstimos:");
        if (usuario.getEmprestimos().isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
        } else {
            for (Emprestimo emp : usuario.getEmprestimos()) {
                Livro livro = emp.getExemplar().getLivro();
                String status = emp.isFinalizado() ? "Finalizado" : "Em curso";
                String dataDev = emp.isFinalizado()
                        ? "Devolvido em: " + emp.getDataRealDevolucao().format(fmt)
                        : "Previsto para: " + emp.getDataPrevistaDevolucao().format(fmt);

                System.out.println("   - " + livro.getTitulo() +
                        " | Empréstimo: " + emp.getDataEmprestimo().format(fmt) +
                        " | " + dataDev +
                        " | Status: " + status);
            }
        }

        // reservas
        System.out.println("Reservas:");
        if (usuario.getReservas().isEmpty()) {
            System.out.println("Nenhuma reserva registrada.");
        } else {
            for (Reserva res : usuario.getReservas()) {
                System.out.println("   - " + res.getLivro().getTitulo() +
                        " | Reservado em: " + res.getDataReserva().format(fmt));
            }
        }
    }
}
