package model.usuarios;

import model.Usuario;
import strategies.RegraEmprestimoProfessor;

public class Professor extends Usuario {
    public Professor(int codigo, String nome) {
        super(codigo, nome);
        this.regraEmprestimo = new RegraEmprestimoProfessor();
    }

    @Override
    public int getPrazoDevolucao() {
        return 8;
    }
}
