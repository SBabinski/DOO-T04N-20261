package aula09;

public class CalculadoraLogica {
public double parseNumero(String texto) throws CalculadoraException {
	if(texto ==null || texto.trim().isEmpty()) {
		throw new CalculadoraException(
				CalculadoraException.TipoErro.CAMPO_VAZIO,
				"Por Favor Preencha os dois campos antes de tentar calcular."
			);
	}
	try {
		return Double.parseDouble(texto.trim());
	}
	catch (NumberFormatException e) {
		throw new CalculadoraException(
				CalculadoraException.TipoErro.ENTRADA_INVALIDA,
				"\"" + texto + "\" não é um número válido Use apenas dígito e ponto ex : 32.4"
				);
		
	}

}


public double calcular(double a, double b, String operacao) throws CalculadoraException {
    switch (operacao) {
        case "+": return a + b;
        case "-": return a - b;
        case "×": return a * b;
        case "÷":
            if (b == 0) {
                throw new CalculadoraException(
                    CalculadoraException.TipoErro.DIVISAO_POR_ZERO,
                    "Divisão por zero é impossível! O segundo número não pode ser 0."
                );
            }
            return a / b;
        default:
            throw new CalculadoraException(
                CalculadoraException.TipoErro.ENTRADA_INVALIDA,
                "Operação desconhecida: " + operacao
            );
    	}
	}
}
