import javax.swing.*;
import java.awt.*;

// Janela principal que organiza os painéis (Responsabilidade: Orquestração)
public class TelaPrincipal extends JFrame {
    private final Biblioteca biblioteca;
    private final PainelListagem painelListagem;

    public TelaPrincipal() {
        super("Controle de Biblioteca");
        this.biblioteca = new Biblioteca();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Usando JTabbedPane para separar as responsabilidades da UI
        JTabbedPane abas = new JTabbedPane();
        
        // O painel de listagem precisa ser atualizado quando um item é adicionado
        // no painel de cadastro. Usamos um callback (Runnable) para isso.
        this.painelListagem = new PainelListagem(biblioteca);
        
        // O painel de cadastro notifica a atualização através do callback
        PainelCadastro painelCadastro = new PainelCadastro(biblioteca, painelListagem::atualizarListagem);

        abas.addTab("Cadastro", painelCadastro);
        abas.addTab("Listagem", painelListagem);

        add(abas);
    }
}