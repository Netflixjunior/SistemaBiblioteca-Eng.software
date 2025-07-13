package model.usuarios;

import model.Usuario;
import strategies.RegraEmprestimoAluno;

public class AlunoPosGraduacao extends Usuario {
    public AlunoPosGraduacao(int codigo, String nome) {
        super(codigo, nome);
        this.regraEmprestimo = new RegraEmprestimoAluno();
    }

    @Override
    public int getPrazoDevolucao() {
        return 5;
    }
}
