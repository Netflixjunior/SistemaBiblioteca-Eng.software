package model.transacoes;

import model.Usuario;
import model.livros.Livro;

import java.time.LocalDate;

public class Reserva {
    private LocalDate dataReserva;
    private Usuario usuario;
    private Livro livro;

    public Reserva(LocalDate dataReserva, Usuario usuario, Livro livro) {
        this.dataReserva = dataReserva;
        this.usuario = usuario;
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public Livro getLivro(){
        return livro;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }
}
