public class Filmes extends ItemBibliotecario {

    private String genero;

    public Filmes(String titulo, String diretor, int ano, String genero) {
        super(titulo, diretor, ano); // "autor" aqui é o diretor
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return String.format("[FILME] %s, Gênero: %s",
                super.toString(), getGenero());
    }

}