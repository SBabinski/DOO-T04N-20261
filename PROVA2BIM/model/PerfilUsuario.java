package tvtracker.model;

import java.util.ArrayList; //implementa lista dinamica
import java.util.List;      //interface da lista generica


//perfil e armazena as listas
public class PerfilUsuario {

    private String      nome;       
    private List<Serie> favoritos;  
    private List<Serie> assistidas; 
    private List<Serie> querVer;    


    //vai incializar com as listas vazias 
    public PerfilUsuario() {
        this.favoritos  = new ArrayList<>(); 
        this.assistidas = new ArrayList<>(); 
        this.querVer    = new ArrayList<>(); 
    }

   
    public PerfilUsuario(String nome) {
        this();            
        this.nome = nome;  
    }

    //getters e setters do perfil
    public String getNome()       { return nome; }         // retorna o nome do usuário
    public void   setNome(String nome) { this.nome = nome; } // define o nome do usuário

    public List<Serie> getFavoritos()  { return favoritos; }  // retorna a lista de favoritos
    public List<Serie> getAssistidas() { return assistidas; } // retorna a lista de assistidas
    public List<Serie> getQuerVer()    { return querVer; }    // retorna a lista de quer ver

    // Setters das listas (usados na deserialização do JSON)
    public void setFavoritos(List<Serie> lista)  { this.favoritos  = lista; } // define favoritos
    public void setAssistidas(List<Serie> lista) { this.assistidas = lista; } // define assistidas
    public void setQuerVer(List<Serie> lista)    { this.querVer    = lista; } // define quer ver

 
//lista de favoritos

//funcao add
    public boolean adicionarFavorito(Serie serie) {
        if (favoritos.contains(serie)) return false; //se ja estiver na lista  n vai duplicar
        return favoritos.add(serie);                  // adiciona e retorna true
    }

//funcao remover
    public boolean removerFavorito(Serie serie) {
        return favoritos.remove(serie); 
    }

   
//funcao q verifica se ja esta na lista
    public boolean isFavorito(Serie serie) {
        return favoritos.contains(serie); // usa equals() para verificar
    }


//lista de assistidos metodo

//add
    public boolean adicionarAssistida(Serie serie) {
        if (assistidas.contains(serie)) return false; //já está na lista
        return assistidas.add(serie);                
    }

//remove da lista
    public boolean removerAssistida(Serie serie) {
        return assistidas.remove(serie); 
    }

//verifica se ja foi assistida
    public boolean isAssistida(Serie serie) {
        return assistidas.contains(serie); //verifica
    }


    //lista de quer ver metod o

//add na lista
    public boolean adicionarQuerVer(Serie serie) {
        if (querVer.contains(serie)) return false; 
        return querVer.add(serie);                  
    }

//remove
    public boolean removerQuerVer(Serie serie) {
        return querVer.remove(serie);
    }

//faz a verficiacao
    public boolean isQuerVer(Serie serie) {
        return querVer.contains(serie); 
    }
}
