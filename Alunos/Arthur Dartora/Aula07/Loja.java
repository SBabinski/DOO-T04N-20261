package Aula07;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Loja {

	String nomeFantasia;
	String razaoSocial;
	String cnpj;
	Endereco endereco;

	Vendedor[] vendedores = new Vendedor[10];
	Cliente[] clientes = new Cliente[10];
	HashMap<LocalDate, Integer> vendasPorDia = new HashMap<>();

	public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.endereco = endereco;
	}

	public void contaClientes() {
		int total = 0;
		for(Cliente clie:clientes) if (clie != null) total++;
		System.out.println("Clientes" + total);
	}

	public void contarVendedor() {
		int total = 0;
		for(Vendedor vend:vendedores) if (vend != null) total++;
		System.out.println("Vendedores" + total);
	}

	public void apresentarSe() {
		System.out.println(nomeFantasia + " - " + cnpj);
		endereco.apresentarLogradouro();
	}

	public void registrarVenda(int dia, int mes, int ano, int quantidade) {
		LocalDate data = LocalDate.of(ano, mes, dia);
		vendasPorDia.put(data, vendasPorDia.getOrDefault(data, 0) + quantidade);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Registrado " + quantidade + " venda(s) em " + data.format(fmt));
	}

	public void buscarVendas(int mes, int dia) {
		int total = 0;
		for(Map.Entry<LocalDate, Integer> entry : vendasPorDia.entrySet()) {
			if(entry.getKey().getMonthValue() == mes && entry.getKey().getDayOfMonth() == dia) {
				total += entry.getValue();
			}
		}
		System.out.println("Total de vendas em " + dia + "/" + mes + ": " + total);
	}
}