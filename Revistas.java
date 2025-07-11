public class Revistas extends ItemBibliotecario {
    
    private int numero;
    private String organizacao;

    public Revistas(String titulo, String autor, int ano, int numero, String organizacao) {
        super(titulo, autor, ano);
        this.numero = numero;
        this.organizacao = organizacao;
    }

    // --- Getters e Setters ---
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public String getOrganizacao() { return organizacao; }
    public void setOrganizacao(String org) { this.organizacao = org; }

    @Override
    public String toString() {
        // Adiciona os detalhes específicos da revista
        return String.format("[REVISTA] %s, Número: %d, Organização: %s",
                super.toString(), getNumero(), getOrganizacao());
    }
}