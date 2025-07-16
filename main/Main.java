package main;

import commands.*;
import model.livros.Exemplar;
import model.livros.Livro;
import model.usuarios.*;
import model.Usuario;
import repository.Repositorio;

import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Repositorio repo = Repositorio.getInstance();

        // Criando usuários
        Usuario u1 = new AlunoGraduacao(123, "João da Silva");
        Usuario u2 = new AlunoPosGraduacao(456, "Luiz Fernando Rodrigues");
        Usuario u3 = new AlunoGraduacao(789, "Pedro Paulo");
        Usuario u4 = new Professor(100, "Carlos Lucena");

        repo.getUsuarios().add(u1);
        repo.getUsuarios().add(u2);
        repo.getUsuarios().add(u3);
        repo.getUsuarios().add(u4);

        // Criando livros
        Livro l1 = new Livro(100, "Engenharia de Software", "Addison Wesley", "Ian Sommervile", "6ª", 2000);
        Livro l2 = new Livro(101, "UML - Guia do Usuário", "Campus", "Grady Booch, James Rumbaugh, Ivar Jacobson", "7ª", 2000);
        Livro l3 = new Livro(200, "Code Complete", "Microsoft Press", "Steve McConnell", "2ª", 2014);
        Livro l4 = new Livro(201, "Agile Software Development, Principles, Patterns and Practices", "Prentice Hall", "Robert Martin", "1ª", 2002);
        Livro l5 = new Livro(300, "Refactoring: Improving the Design of Existing Code", "Addison Wesley Professional", "Martin Fowler", "1ª", 1999);
        Livro l6 = new Livro(301, "Software Metrics: A rigorous and Practical Approach", "CRC Press", "Norman Fenton, James Bieman", "3ª", 2014);
        Livro l7 = new Livro(400, "Design Patterns: Element of Reusable Object-Oriented Software", "Addison Wesley Professional", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "1ª", 1994);
        Livro l8 = new Livro(401, "UML Distilled: A Brief Guide to the Standard Object Modeling Language", "Addison Wesley Professional", "Martin Fowler", "3ª", 2003);

        repo.getLivros().add(l1);
        repo.getLivros().add(l2);
        repo.getLivros().add(l3);
        repo.getLivros().add(l4);
        repo.getLivros().add(l5);
        repo.getLivros().add(l6);
        repo.getLivros().add(l7);
        repo.getLivros().add(l8);

        // Criando exemplares dos livros
        l1.adicionarExemplar(new Exemplar(1, l1 ));  // Exemplar 01 do livro 100
        l1.adicionarExemplar(new Exemplar(2, l1));  // Exemplar 02 do livro 100

        l2.adicionarExemplar(new Exemplar(3, l2));  // Exemplar 03 do livro 101

        l3.adicionarExemplar(new Exemplar(4, l3));  // Exemplar 04 do livro 200

        l4.adicionarExemplar(new Exemplar(5, l4));  // Exemplar 05 do livro 201

        l5.adicionarExemplar(new Exemplar(6, l5));  // Exemplar 06 do livro 300
        l5.adicionarExemplar(new Exemplar(7, l5));  // Exemplar 07 do livro 300

        l7.adicionarExemplar(new Exemplar(8, l7));  // Exemplar 08 do livro 400
        l7.adicionarExemplar(new Exemplar(9, l7));  // Exemplar 09 do livro 400




        LeitorComandos leitor = new LeitorComandos();
        System.out.println("Sistema de Biblioteca iniciado. Digite um comando:");
        while (true) {
            String linha = scanner.nextLine();
            if (linha.equals("sai")) break;
            leitor.processarLinha(linha);
        }
        scanner.close();
    }
}
