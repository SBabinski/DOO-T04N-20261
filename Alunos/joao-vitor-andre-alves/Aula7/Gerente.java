public class Gerente extends Funcionario {

	// CALCULA O BONUS PARA O REGISTRO DO SALARIO DO GERENTE
	public float calcularBonus() {
		float bonus = salarioBase * 0.35f;

		return bonus;
	}
}
