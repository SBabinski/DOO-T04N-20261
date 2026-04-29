import java.time.LocalDate;

public class Venda {

	private int quantidade;
	private float valorUnitario;
	private float valorTotal;
	private float desconto;
	private float valorComDesconto;
	private LocalDate dataVenda;

	// GETTERS E SETTERS

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public float getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(float valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public float getDesconto() {
		return desconto;
	}

	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}

	public float getValorComDesconto() {
		return valorComDesconto;
	}

	public void setValorComDesconto(float valorComDesconto) {
		this.valorComDesconto = valorComDesconto;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

}
