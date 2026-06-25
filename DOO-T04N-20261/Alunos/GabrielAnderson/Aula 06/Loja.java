import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cpnj;
    private String cidade;
    private String bairro;
    private String rua;
    private Vendedor[] vendedores;
    private Cliente[] clientes;
    private List<Venda> registroDeVendas = new ArrayList<>();
    private double ultimoTotalCalculado = 0.0;

    public Loja(String nomeFantasia, String razaoSocial, String cpnj, String cidade, String bairro, String rua, Vendedor[] vendedores, Cliente[] clientes) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cpnj = cpnj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.vendedores = vendedores;
        this.clientes = clientes;
    }

    public int contarClientes() {
        return clientes.length;
    }

    public int contarVendedores() {
        return vendedores.length;
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia + ", CNPJ: " + cpnj + ", Endereco: " + cidade + ", " + bairro + ", " + rua);
    }

    public void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Quantidade vendida: ");
        double quantidade = scanner.nextDouble();
        System.out.print("Preço unitário: R$ ");
        double precoUnitario = scanner.nextDouble();

        double totalSemDesconto = quantidade * precoUnitario;
        double desconto = 0.0;
        double totalComDesconto = totalSemDesconto;

        if (quantidade > 10) {
            desconto = totalSemDesconto * 0.05;
            totalComDesconto = totalSemDesconto - desconto;
            System.out.printf("Desconto especial aplicado: R$ %.2f\n", desconto);
        }

        System.out.printf("Preço total da venda: R$ %.2f\n", totalComDesconto);

        Venda venda = new Venda(quantidade, precoUnitario, desconto, totalComDesconto, LocalDate.now());
        registroDeVendas.add(venda);
        ultimoTotalCalculado = totalComDesconto;
    }

    public void calcularTroco(Scanner scanner) {
        System.out.print("Valor recebido do cliente: R$ ");
        double valorRecebido = scanner.nextDouble();

        double valorTotalCompra = ultimoTotalCalculado;
        if (valorTotalCompra == 0.0) {
            System.out.print("Valor total da compra: R$ ");
            valorTotalCompra = scanner.nextDouble();
        }

        double troco = valorRecebido - valorTotalCompra;
        System.out.printf("Troco a ser devolvido: R$ %.2f\n", troco);
    }

    public void exibirRegistroDeVendas() {
        if (registroDeVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada ainda.");
        } else {
            System.out.println("\n== Registro de Vendas ==");
            for (int i = 0; i < registroDeVendas.size(); i++) {
                Venda venda = registroDeVendas.get(i);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = venda.getData().format(formatter);
                System.out.printf("Venda %d: Data: %s, Quantidade: %.0f, Preço Unitário: R$ %.2f, Desconto: R$ %.2f, Total: R$ %.2f\n",
                        i + 1, dataFormatada, venda.getQuantidade(), venda.getPrecoUnitario(), venda.getDesconto(), venda.getTotal());
            }
        }
    }

    public double calcularQuantidadeTotalDia(int ano, int mes, int dia) {
        double totalQuantidade = 0.0;
        for (Venda venda : registroDeVendas) {
            if (venda.getData().getYear() == ano && venda.getData().getMonthValue() == mes && venda.getData().getDayOfMonth() == dia) {
                totalQuantidade += venda.getQuantidade();
            }
        }
        return totalQuantidade;
    }

    public double buscarQuantidadeTotalMesDia(int mes, int dia) {
        int anoAtual = LocalDate.now().getYear();
        return calcularQuantidadeTotalDia(anoAtual, mes, dia);
    }

    public void calcularQuantidadeTotalDiaMenu(Scanner scanner) {
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        System.out.print("Mês: ");
        int mes = scanner.nextInt();
        System.out.print("Dia: ");
        int dia = scanner.nextInt();

        double total = calcularQuantidadeTotalDia(ano, mes, dia);
        System.out.printf("Quantidade total de vendas em %02d/%02d/%d: %.0f\n", dia, mes, ano, total);
    }

    public void buscarQuantidadeTotalMesDiaMenu(Scanner scanner) {
        System.out.print("Mês: ");
        int mes = scanner.nextInt();
        System.out.print("Dia: ");
        int dia = scanner.nextInt();

        double total = buscarQuantidadeTotalMesDia(mes, dia);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = LocalDate.of(LocalDate.now().getYear(), mes, dia).format(formatter);
        System.out.printf("Quantidade total de vendas em %s: %.0f\n", dataFormatada, total);
    }
}