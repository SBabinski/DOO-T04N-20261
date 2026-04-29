public class Gerente extends Funcionario {

	public float calcularBonus() {
		float bonus = salarioBase * 0.35f;

		return bonus;
	}
}