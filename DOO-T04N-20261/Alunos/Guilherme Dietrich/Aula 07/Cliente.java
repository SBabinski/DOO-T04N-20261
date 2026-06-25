package Objetos;

public class Cliente extends Pessoa {

    public void CadastroDoCliente() {
        System.out.println("--------------------------------");
        System.out.println("NOME: " + nome);
        System.out.println("IDADE: " + idade);
        System.out.println("ENDEREÇO: " + endereco.apresentarLogradouro());
        System.out.println("--------------------------------");
    }
}