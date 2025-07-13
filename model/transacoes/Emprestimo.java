package model.transacoes;

// importa as classes Usuario e Exemplar, pois um empréstimo está ligado a um usuário e a um exemplar.
import java.time.LocalDate;
import model.Usuario;
import model.livros.Exemplar;


public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean finalizado = false;

    //Para ligar o empréstimo a quem pegou o livro e a qual exemplar foi emprestado.
    private Usuario usuario;
    private Exemplar exemplar;

    public Emprestimo(LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }
    //Associa esse empréstimo a um usuário
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    //Associa esse emprestimo a um exemplar
    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void finalizar() {
        this.finalizado = true;
    }
}
