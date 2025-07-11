import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Painel com a responsabilidade única de cadastrar itens
public class PainelCadastro extends JPanel {
    private final Biblioteca biblioteca;
    private final Runnable callbackAtualizacao; // Para notificar a outra tela

    // Campos comuns
    private final JTextField txtTitulo = new JTextField(20);
    private final JTextField txtAutor = new JTextField(20);
    private final JTextField txtAno = new JTextField(5);

    // Campos da Revista
    private final JTextField txtNumeroRevista = new JTextField(5);
    private final JTextField txtOrgRevista = new JTextField(15);

    // Campos do Filme
    private final JTextField txtGeneroFilme = new JTextField(15);

    private final JComboBox<String> tipoItemComboBox;
    private final JPanel painelCamposDinamicos;
    private final CardLayout cardLayout = new CardLayout();

    public PainelCadastro(Biblioteca biblioteca, Runnable callbackAtualizacao) {
        this.biblioteca = biblioteca;
        this.callbackAtualizacao = callbackAtualizacao;
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Cadastro de Novo Item"));

        // Painel para seleção de tipo
        JPanel painelSelecao = new JPanel();
        tipoItemComboBox = new JComboBox<>(new String[]{"Livro", "Revista", "Filme"});
        painelSelecao.add(new JLabel("Tipo de Item:"));
        painelSelecao.add(tipoItemComboBox);
        add(painelSelecao, BorderLayout.NORTH);

        // Painel central com os campos
        JPanel painelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Adicionando campos comuns
        gbc.gridx = 0; gbc.gridy = 0; painelCampos.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; painelCampos.add(txtTitulo, gbc);
        gbc.gridx = 0; gbc.gridy = 1; painelCampos.add(new JLabel("Autor/Diretor:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; painelCampos.add(txtAutor, gbc);
        gbc.gridx = 0; gbc.gridy = 2; painelCampos.add(new JLabel("Ano:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; painelCampos.add(txtAno, gbc);

        // Painel dinâmico para campos específicos
        painelCamposDinamicos = new JPanel(cardLayout);
        
        // Card para Revista
        JPanel painelRevista = new JPanel(new GridLayout(2, 2, 5, 5));
        painelRevista.add(new JLabel("Número:"));
        painelRevista.add(txtNumeroRevista);
        painelRevista.add(new JLabel("Organização:"));
        painelRevista.add(txtOrgRevista);
        
        // Card para Filme
        JPanel painelFilme = new JPanel(new GridLayout(1, 2, 5, 5));
        painelFilme.add(new JLabel("Gênero:"));
        painelFilme.add(txtGeneroFilme);

        painelCamposDinamicos.add(new JPanel(), "Livro"); // Painel vazio para Livro
        painelCamposDinamicos.add(painelRevista, "Revista");
        painelCamposDinamicos.add(painelFilme, "Filme");

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        painelCampos.add(painelCamposDinamicos, gbc);
        
        add(painelCampos, BorderLayout.CENTER);

        // Listener para o ComboBox
        tipoItemComboBox.addActionListener(e -> {
            String itemSelecionado = (String) tipoItemComboBox.getSelectedItem();
            cardLayout.show(painelCamposDinamicos, itemSelecionado);
        });

        // Botão de Inclusão
        JButton btnIncluir = new JButton("Incluir");
        btnIncluir.addActionListener(this::incluirItem);
        
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.add(btnIncluir);
        add(painelBotao, BorderLayout.SOUTH);
    }

    private void incluirItem(ActionEvent e) {
        try {
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            int ano = Integer.parseInt(txtAno.getText());

            if (titulo.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Título e Autor/Diretor são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String tipoSelecionado = (String) tipoItemComboBox.getSelectedItem();
            ItemBibliotecario novoItem = null;

            switch (tipoSelecionado) {
                case "Livro":
                    novoItem = new Livros(titulo, autor, ano);
                    break;
                case "Revista":
                    int numero = Integer.parseInt(txtNumeroRevista.getText());
                    String org = txtOrgRevista.getText();
                    novoItem = new Revistas(titulo, autor, ano, numero, org);
                    break;
                case "Filme":
                    String genero = txtGeneroFilme.getText();
                    novoItem = new Filmes(titulo, autor, ano, genero);
                    break;
            }

            if (novoItem != null) {
                biblioteca.adicionarItem(novoItem);
                JOptionPane.showMessageDialog(this, tipoSelecionado + " incluído com sucesso!");
                limparCampos();
                callbackAtualizacao.run(); // Notifica a outra tela para atualizar a listagem
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ano e Número devem ser valores numéricos.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtTitulo.setText("");
        txtAutor.setText("");
        txtAno.setText("");
        txtNumeroRevista.setText("");
        txtOrgRevista.setText("");
        txtGeneroFilme.setText("");
    }
}