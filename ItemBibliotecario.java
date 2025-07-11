public abstract class ItemBibliotecario implements ItoStringProvider {
    private String titulo;
    private String autor; // Usado para autor do livro, diretor do filme, etc.
    private int ano;

    public ItemBibliotecario(String titulo, String autor, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }
    
    // --- Getters e Setters --- 
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    @Override
    public String toString() {
        return String.format("Título: %s, Autor/Diretor: %s, Ano: %d",
                getTitulo(), getAutor(), getAno());
    }
}