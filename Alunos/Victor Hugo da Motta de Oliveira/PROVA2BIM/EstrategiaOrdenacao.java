import java.util.List;

// polimorfismo
public interface EstrategiaOrdenacao {
    // metodo que vai ser implementado de jeitos diferentes nas outras classes
    void ordenar(List<Serie> lista);
}