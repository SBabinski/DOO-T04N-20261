package provaBimestral;

public class Quartos {
public int numeroQuarto;
public double valorDiaria;
private boolean disponivel = true;

public void cadastrarQuartos(int numeroQuarto, double valorDiaria) {
	this.numeroQuarto = numeroQuarto;
	this.valorDiaria = valorDiaria;
}
public boolean isDisponivel() { return disponivel; }
public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}
