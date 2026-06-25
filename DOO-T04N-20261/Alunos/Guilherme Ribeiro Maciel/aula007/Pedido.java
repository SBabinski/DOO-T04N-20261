package calculadora;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Pedido {
	
	int id;
	LocalDate dataCriacao;
	LocalDate dataPagamento;
	LocalDate dataVencimentoReserva;
	int cliente;
	int vendedor;
	int loja;
	ArrayList<Item> itens = new ArrayList<>();
	
	public ArrayList<Item> getItens() {
		return itens;
	}

	public void setItens(Item itens) {
		this.itens.add(itens);
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public LocalDate getDataVencimentoReserva() {
		return dataVencimentoReserva;
	}
	
	public void setDataVencimentoReserva(LocalDate dataVencimentoReserva) {
		this.dataVencimentoReserva = dataVencimentoReserva;
	}
	
	public int getCliente() {
		return cliente;
	}
	
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	
	public int getVendedor() {
		return vendedor;
	}
	
	public void setVendedor(int vendedor) {
		this.vendedor = vendedor;
	}
	
	public int getLoja() {
		return loja;
	}
	
	public void setLoja(int loja) {
		this.loja = loja;
	}
	
	public String dataFormBr(LocalDate data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataBr = data.format(formatter);
		return dataBr;
	}
	
	public float calculaValorTotal() {
		int soma = 0;
		for(int i=0; i < itens.size(); i++) {
			soma += itens.get(i).getValor();
		}
		return soma;
	}
	
	public String descricaoVenda() {
		return "ID: " + id + "|" + dataFormBr(dataCriacao) + "|" + calculaValorTotal();
	}
}