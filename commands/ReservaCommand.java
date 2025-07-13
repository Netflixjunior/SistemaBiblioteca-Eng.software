package commands;

import model.Usuario;
import model.livros.Livro;
import model.transacoes.Reserva;
import repository.Repositorio;

import java.time.LocalDate;

public class ReservaCommand implements Comando {
    @Override
    public void executar(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: res <codigoUsuario> <codigoLivro>");
            return;
        }

        int codUsuario = Integer.parseInt(args[1]);
        int codLivro = Integer.parseInt(args[2]);

        Repositorio repo = Repositorio.getInstance();
        Usuario usuario = repo.getUsuarios().stream()
                .filter(u -> u.getCodigo() == codUsuario)
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        Livro livro = repo.getLivros().stream()
                .filter(l -> l.getCodigo() == codLivro)
                .findFirst()
                .orElse(null);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        // Verifica se o usuário já fez uma reserva para esse livro
        boolean jaReservou = usuario.getReservas().stream()
                .anyMatch(r -> r.getLivro().equals(livro));

        if (jaReservou) {
            System.out.println("Usuário já reservou este livro.");
            return;
        }

        // Cria e registra a reserva no usuário e no livro
        Reserva novaReserva = new Reserva(LocalDate.now(), usuario, livro);
        usuario.getReservas().add(novaReserva);
        livro.getReservas().add(novaReserva);

        System.out.println("Reserva registrada com sucesso.");
    }
}
