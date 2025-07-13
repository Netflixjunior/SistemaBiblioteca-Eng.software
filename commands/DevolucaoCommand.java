package commands;

import model.Usuario;
import model.livros.Exemplar;
import model.livros.Livro;
import model.transacoes.Emprestimo;
import repository.Repositorio;
import utils.Busca;

public class DevolucaoCommand implements Comando {
    @Override
    public void executar(String[] args) {
        if (args.length < 3) {
            System.out.println("Modo de Uso: dev <codigoUsuario> <codigoLivro>");
            return;
        }

        int codUsuario = Integer.parseInt(args[1]);
        int codLivro = Integer.parseInt(args[2]);

        Usuario usuario = Busca.buscarUsuario(codUsuario);
        if (usuario == null) {return; }

        Livro livro = Busca.buscarLivro(codLivro);
        if (livro == null) {return;}

        // Percorre todos os empréstimos do usuário.
        // Acha o primeiro que ainda está em aberto e cujo exemplar seja de um livro igual ao que o usuário está tentando devolver.
        // Se não encontrar nenhum, retorna null.
        Emprestimo emprestimo = usuario.getEmprestimos().stream()
                .filter(e -> !e.isFinalizado() && e.getExemplar().getLivro().equals(livro))
                .findFirst()
                .orElse(null);

        if (emprestimo == null) {
            System.out.println("Nenhum empréstimo ativo encontrado para este livro.");
            return;
        }

        // Marcar devolução
        emprestimo.finalizar();
        emprestimo.getExemplar().setDisponivel(true);

        System.out.println("Devolução registrada com sucesso.");
    }
}
