package commands;

import model.Usuario;
import observer.Observador;
import utils.Busca;

public class ConsultaNotificacoesCommand implements Comando {
    @Override
    public void executar(String[] args) {
        if (args.length < 2) {
            System.out.println("Modo de Uso: ntf <codigoUsuario>");
            return;
        }

        int codUsuario = Integer.parseInt(args[1]);

        Usuario usuario = Busca.buscarUsuario(codUsuario);
        if (usuario == null) return;

        if (usuario instanceof Observador) {

            try {
                // chama getNotificacoesRecebidas() sem saber a classe
                int total = (int) usuario.getClass()
                        .getMethod("getNotificacoesRecebidas")
                        .invoke(usuario);

                System.out.println("Notificações recebidas: " + total);
            } catch (Exception e) {
                System.out.println("Erro ao acessar notificações.");
            }

        } else {
            System.out.println("Este usuário não é um observador.");
        }
    }
}
