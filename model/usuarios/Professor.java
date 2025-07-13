package model.usuarios;

import observer.Observador;
import model.Usuario;
import strategies.RegraEmprestimoProfessor;

public class Professor extends Usuario implements Observador {
    private int notificacoesRecebidas = 0;

    public Professor(int codigo, String nome) {
        super(codigo, nome);
        this.regraEmprestimo = new RegraEmprestimoProfessor();
    }

    @Override
    public int getPrazoDevolucao() {
        return 8;
    }

    @Override
    public void notificar() {
        notificacoesRecebidas++;
    }

    public int getNotificacoesRecebidas(){
        return notificacoesRecebidas;
    }
}