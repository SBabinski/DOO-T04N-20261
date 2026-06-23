package aula09;

public class CalculadoraException extends Exception{
public enum TipoErro{
	DIVISAO_POR_ZERO,
	ENTRADA_INVALIDA,
	CAMPO_VAZIO
}
private TipoErro tipoErro;
public CalculadoraException(TipoErro tipoErro, String mensagem) {
	super(mensagem);
	this.tipoErro = tipoErro;
}

public TipoErro getTipoErro() {
	return tipoErro;
}

}
