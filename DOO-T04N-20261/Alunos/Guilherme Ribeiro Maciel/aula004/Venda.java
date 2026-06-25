package calculadora;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Venda {
	private int qtd;
	private float valorUni;
	private LocalDate data;
	
	public Venda() {
		
	}
	
	public Venda(float valorUni, int qtd, LocalDate data) {
		setValorUni(valorUni);
		setQtd(qtd);
		setData(data);
	}
	
	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public int getQtd() {
		return qtd;
	}
	
	public void setQtd(int qtd) {
		if (qtd > 0 ) {
			this.qtd = qtd;
		}
	}
	
	public float getValorUni() {
		return valorUni;
	}
	
	public void setValorUni(float valorUni) {
		if(valorUni > 0.0f) {
			this.valorUni = valorUni;
		}
	}
	
	public double vlTotal() {
		if (qtd>10) {
			return (valorUni - (valorUni * 0.05)) * qtd;
		}else {
			return valorUni * qtd;
		}
	}
	
	public String dataFormBr(LocalDate data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataBr = data.format(formatter);
		return dataBr;
	}
	
	public String resumo() {
		return dataFormBr(data) + "||" + valorUni + "||" + qtd + "||" + vlTotal();
	}
}
