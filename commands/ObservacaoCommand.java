package commands;

import model.Usuario;
import model.livros.Livro;
import observer.Observador;
import utils.Busca;

public class ObservacaoCommand implements Comando {
    @Override
    public void executar(String[] args) {
        if (args.length < 3) {
            System.out.println("Modo de Uso: obs <codigoUsuario> <codigoLivro>");
            return;
        }

        int codUsuario = Integer.parseInt(args[1]);
        int codLivro = Integer.parseInt(args[2]);

        Usuario usuario = Busca.buscarUsuario(codUsuario);
        if (usuario == null) {return; }

        Livro livro = Busca.buscarLivro(codLivro);
        if (livro == null) {return;}



        if (usuario instanceof Observador) {
            livro.adicionarObservador((Observador) usuario);
            System.out.println("Observador registrado com sucesso.");
        } else {
            System.out.println("Este usuário não pode ser registrado como observador.");
        }
    }
}
