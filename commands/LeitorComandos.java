package commands;

import java.util.HashMap;
import java.util.Map;

public class LeitorComandos {
    private Map<String, Comando> comandos = new HashMap<>();

    public LeitorComandos() {
        comandos.put("emp", new EmprestimoCommand());
        comandos.put("dev", new DevolucaoCommand());
        comandos.put("res", new ReservaCommand());
        comandos.put("obs", new ObservacaoCommand());
        comandos.put("liv", new ConsultaLivroCommand());
        comandos.put("usu", new ConsultaUsuarioCommand());
        comandos.put("ntf", new ConsultaNotificacoesCommand());
    }

    public void processarLinha(String linha) {
        String[] partes = linha.split(" ");
        if (partes.length == 0) return;

        String comando = partes[0];
        Comando c = comandos.get(comando);

        if (c != null) {
            c.executar(partes);
        } else {
            System.out.println("Comando desconhecido: " + comando);
        }
    }
}
