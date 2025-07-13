package model.livros;

public class Exemplar {
    private int codigo;
    private boolean disponivel = true;
    private Livro livro;

    public Exemplar(int codigo, Livro livro) {
        this.codigo = codigo;
        this.livro = livro;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getCodigo() {
        return codigo;
    }

    public Livro getLivro() {
        return livro;
    }
}
