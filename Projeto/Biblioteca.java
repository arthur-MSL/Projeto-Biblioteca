import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    // Usando a classe base para permitir polimorfismo
    private final List<ItemBibliotecario> acervo;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
    }

    public void adicionarItem(ItemBibliotecario item) {
        this.acervo.add(item);
    }

    public List<ItemBibliotecario> getAcervo() {
        return acervo;
    }
}