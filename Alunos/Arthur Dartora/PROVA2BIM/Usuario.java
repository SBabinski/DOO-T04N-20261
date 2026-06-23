//guarda o usuario local e as listas
public class Usuario {

    private String nome;

    //os atributos sao da classe ListaDeSeries, mas guardam os objetos das subclasses
    private ListaDeSeries favoritos;
    private ListaDeSeries assistidas;
    private ListaDeSeries desejaAssistir;

    public Usuario(String nome) {
        this.nome = nome;
        this.favoritos = new ListaFavoritos();
        this.assistidas = new ListaAssistidas();
        this.desejaAssistir = new ListaDesejaAssistir();
    }

    public String getNome() {
        return nome;
    }

    public ListaDeSeries getFavoritos() {
        return favoritos;
    }

    public ListaDeSeries getAssistidas() {
        return assistidas;
    }

    public ListaDeSeries getDesejaAssistir() {
        return desejaAssistir;
    }
}
