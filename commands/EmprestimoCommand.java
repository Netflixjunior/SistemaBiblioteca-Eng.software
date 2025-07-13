package commands;

import model.Usuario;
import model.livros.Exemplar;
import model.livros.Livro;
import model.transacoes.Emprestimo;
import model.transacoes.Reserva;
import repository.Repositorio;

import java.time.LocalDate;

public class EmprestimoCommand implements Comando {

    @Override
    public void executar(String[] args) {

        // Verifica se a entrada tem os 2 argumentos (codigo do usuário e do livro)
        if (args.length < 3) {
            System.out.println("Modo de Uso: emp <codigoUsuario> <codigoLivro>");
            return;
        }

        // Separa a string em inteiros
        int codUsuario = Integer.parseInt(args[1]);
        int codLivro = Integer.parseInt(args[2]);

        //Acessa o repositório
        Repositorio repo = Repositorio.getInstance();

        //Busca Usuário e livro informada, se não forem encontrados imprime "Usuário/Livro não encontrado"
        //__________________________________________________________________________
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
        //__________________________________________________________________________

        // Verifica regra de empréstimo
        boolean pode = usuario.getRegraEmprestimo().podeEmprestar(usuario, livro);
        if (!pode) {
            System.out.println("Não foi possível realizar o empréstimo. Verifique as regras.");
            return;
        }

        // Obter exemplar disponível se não tiver nenhum disponível imprime "Não há exemplares disponíveis."
        Exemplar exemplarDisponivel = livro.getExemplares().stream()
                .filter(Exemplar::isDisponivel)
                .findFirst()
                .orElse(null);

        if (exemplarDisponivel == null) {
            System.out.println("Não há exemplares disponíveis.");
            return;
        }

        // Remover reserva do usuário, se existir
        livro.getReservas().removeIf(reserva -> reserva.getUsuario().equals(usuario));
        usuario.getReservas().removeIf(reserva -> reserva.getLivro().equals(livro)); //?? se houver isso em Usuario

        // Marcar exemplar como emprestado
        exemplarDisponivel.setDisponivel(false);

        // Determinar prazo de devolução
        int diasEmprestimo = usuario.getPrazoDevolucao();
        LocalDate hoje = LocalDate.now();
        LocalDate devolucao = hoje.plusDays(diasEmprestimo);

        // Criar e associar empréstimo
        Emprestimo emprestimo = new Emprestimo(hoje, devolucao);
        emprestimo.setUsuario(usuario);
        emprestimo.setExemplar(exemplarDisponivel);

        usuario.getEmprestimos().add(emprestimo);

        System.out.println("Empréstimo realizado com sucesso! Data de devolução: " + devolucao);
    }
}
