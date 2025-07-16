package commands;

import model.Usuario;
import model.livros.Livro;
import model.transacoes.Reserva;
import repository.Repositorio;
import utils.Busca;

import java.time.LocalDate;

public class ReservaCommand implements Comando {
    @Override
    public void executar(String[] args) {
        if (args.length < 3) {
            System.out.println("Mode de Uso: res <codigoUsuario> <codigoLivro>");
            return;
        }

        int codUsuario = Integer.parseInt(args[1]);
        int codLivro = Integer.parseInt(args[2]);

        Usuario usuario = Busca.buscarUsuario(codUsuario);
        if (usuario == null) {return; }

        Livro livro = Busca.buscarLivro(codLivro);
        if (livro == null) {return; }


        // verifica se o usuário já fez uma reserva para esse livro
        boolean jaReservou = usuario.getReservas().stream()
                .anyMatch(r -> r.getLivro().equals(livro));

        if (jaReservou) {
            System.out.println("Usuário já reservou este livro.");
            return;
        }

        // cria e registra a reserva no usuário e no livro
        Reserva novaReserva = new Reserva(LocalDate.now(), usuario, livro);
        usuario.getReservas().add(novaReserva);
        livro.adicionarReserva(novaReserva);

        System.out.println("Reserva registrada com sucesso.");
    }
}
