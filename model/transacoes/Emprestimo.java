package model.transacoes;

import java.time.LocalDate;
import model.Usuario;
import model.livros.Exemplar;


public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataRealDevolucao;

    private boolean finalizado = false;


    private Usuario usuario;
    private Exemplar exemplar;

    public Emprestimo(LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataRealDevolucao(){
        return dataRealDevolucao;
    }

    public boolean isFinalizado() {
        return finalizado;

    }

    public void finalizar() {
        this.finalizado = true;
        this.dataRealDevolucao = LocalDate.now();
    }
}
