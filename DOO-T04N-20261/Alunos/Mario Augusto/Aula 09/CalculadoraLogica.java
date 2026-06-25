/**
 * Classe responsável pela lógica das operações matemáticas da Calculadora.
 * Valida entradas e lança CalculadoraException para erros específicos.
 */
public class CalculadoraLogica {

    /**
     * Valida e converte uma String em double.
     *
     * @param texto Texto digitado pelo usuário
     * @param nomeCampo Nome do campo para mensagem de erro
     * @return Valor numérico convertido
     * @throws CalculadoraException Se o texto for vazio ou não numérico
     */
    public double parseNumero(String texto, String nomeCampo) throws CalculadoraException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new CalculadoraException(
                "O campo \"" + nomeCampo + "\" está vazio. Por favor, informe um número.",
                CalculadoraException.TipoErro.CAMPO_VAZIO
            );
        }

        try {
            return Double.parseDouble(texto.trim().replace(",", "."));
        } catch (NumberFormatException e) {
            throw new CalculadoraException(
                "Entrada inválida no campo \"" + nomeCampo + "\": \"" + texto.trim()
                + "\" não é um número válido.",
                CalculadoraException.TipoErro.ENTRADA_INVALIDA,
                e
            );
        }
    }

    /**
     * Realiza a soma de dois números.
     */
    public double somar(double a, double b) throws CalculadoraException {
        double resultado = a + b;
        verificarOverflow(resultado);
        return resultado;
    }

    /**
     * Realiza a subtração de dois números.
     */
    public double subtrair(double a, double b) throws CalculadoraException {
        double resultado = a - b;
        verificarOverflow(resultado);
        return resultado;
    }

    /**
     * Realiza a multiplicação de dois números.
     */
    public double multiplicar(double a, double b) throws CalculadoraException {
        double resultado = a * b;
        verificarOverflow(resultado);
        return resultado;
    }

    /**
     * Realiza a divisão de dois números.
     *
     * @throws CalculadoraException Se o divisor for zero
     */
    public double dividir(double a, double b) throws CalculadoraException {
        if (b == 0) {
            throw new CalculadoraException(
                "Divisão por zero não é permitida. O segundo número não pode ser 0.",
                CalculadoraException.TipoErro.DIVISAO_POR_ZERO
            );
        }
        double resultado = a / b;
        verificarOverflow(resultado);
        return resultado;
    }

    /**
     * Verifica se o resultado é infinito (overflow numérico).
     */
    private void verificarOverflow(double resultado) throws CalculadoraException {
        if (Double.isInfinite(resultado) || Double.isNaN(resultado)) {
            throw new CalculadoraException(
                "O resultado está fora do intervalo numérico suportado.",
                CalculadoraException.TipoErro.OVERFLOW
            );
        }
    }

    /**
     * Formata o resultado removendo ".0" desnecessário para inteiros.
     *
     * @param valor Resultado numérico
     * @return String formatada
     */
    public String formatarResultado(double valor) {
        if (valor == Math.floor(valor) && !Double.isInfinite(valor)) {
            return String.valueOf((long) valor);
        }
        // Limita casas decimais a 10 e remove zeros à direita
        String formatado = String.format("%.10f", valor).replaceAll("0+$", "").replaceAll("\\.$", "");
        return formatado;
    }
}
