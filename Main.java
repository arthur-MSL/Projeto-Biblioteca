import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Garante que a UI seja criada na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}