import javax.swing.*;
import java.awt.*;

// Painel com a responsabilidade única de listar os itens
public class PainelListagem extends JPanel {
    private final Biblioteca biblioteca;
    private final JTextArea areaTextoListagem;

    public PainelListagem(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Listagem de Itens no Acervo"));

        areaTextoListagem = new JTextArea();
        areaTextoListagem.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(areaTextoListagem);
        
        add(scrollPane, BorderLayout.CENTER);
        
        // Inicia com a listagem atualizada
        atualizarListagem();
    }

    // Este método demonstra a "Ligação Dinâmica" e o "Princípio Aberto/Fechado"
    public void atualizarListagem() {
        StringBuilder sb = new StringBuilder();
        // Percorre a lista e chama toString() de forma polimórfica
        for (ItemBibliotecario item : biblioteca.getAcervo()) {
            sb.append(item.toString()).append("\n"); // Ligação Dinâmica ocorre aqui!
        }
        areaTextoListagem.setText(sb.toString());
    }
}