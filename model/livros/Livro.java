package model.livros;

import model.Usuario;
import model.transacoes.Reserva;

import java.util.ArrayList;
import java.util.List;

public class Livro {
    private int codigo;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private int anoPublicacao;

    private List<Exemplar> exemplares = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    public Livro(int codigo, String titulo, String editora, String autores, String edicao, int ano) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = ano;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Exemplar> getExemplares() {
        return exemplares;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void adicionarExemplar(Exemplar exemplar) {
        exemplares.add(exemplar);
    }

    public Exemplar buscarExemplarDisponivel() {
        for (Exemplar exemplar : exemplares) {
            if (exemplar.isDisponivel()) {
                return exemplar;
            }
        }
        return null;
    }


    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void removerReservaDoUsuario(Usuario usuario) {
        reservas.removeIf(reserva -> reserva.getUsuario().equals(usuario));
    }

}
