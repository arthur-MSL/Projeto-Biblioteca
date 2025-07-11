public class Livros extends ItemBibliotecario {

    public Livros(String titulo, String autor, int ano) {
        super(titulo, autor, ano);
    }

    @Override
    public String toString() {
        // Chama o toString da superclasse e adiciona o tipo
        return "[LIVRO] " + super.toString();
    }
}