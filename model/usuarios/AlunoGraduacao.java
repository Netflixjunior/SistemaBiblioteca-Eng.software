package model.usuarios;

import model.Usuario;
import strategies.RegraEmprestimoAluno;

public class AlunoGraduacao extends Usuario {
    public AlunoGraduacao(int codigo, String nome) {
        super(codigo, nome);
        this.regraEmprestimo = new RegraEmprestimoAluno();
    }

    @Override
    public int getPrazoDevolucao() {
        return 4;
    }
}
