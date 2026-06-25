package provaBimestral;

public class Pessoa {
public int id;
public String nome;
public String cpf;

public Pessoa( int id, String nome, String cpf)
{
	this.nome = nome;
	this.cpf = cpf;
	this.id = id;
System.out.println("SUCCES :"+ "id : " + id + " + nome + " + "| cadastrado com Sucesso | ");
}
public String getNome() {
	// TODO Auto-generated method stub
	return nome;
}
@Override
public String toString() {
    return "Nome: " + nome + 
           " | ID: " + id;
}
}