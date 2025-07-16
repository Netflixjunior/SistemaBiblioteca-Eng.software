package model;

import model.transacoes.Emprestimo;
import model.transacoes.Reserva;
import strategies.RegraEmprestimo;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    protected int codigo;
    protected String nome;
    protected List<Emprestimo> emprestimos = new ArrayList<>();
    protected List<Reserva> reservas = new ArrayList<>();
    protected RegraEmprestimo regraEmprestimo;

    public Usuario(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public RegraEmprestimo getRegraEmprestimo() {
        return regraEmprestimo;
    }

    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public abstract int getPrazoDevolucao();

    public int getLimiteEmprestimos() {
        return 0;
    }

}



