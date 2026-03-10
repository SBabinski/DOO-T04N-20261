package calculadora.service;

import java.util.Scanner;

public class CalculadoraService {
    Scanner scan = new Scanner(System.in);
    public void CalcularPrecoTotal() {
        System.out.println("Digite a quantidade de itens vendidos: ");
        int quantidadeItens = scan.nextInt();

        System.out.println("Digite o valor do item: ");
        double valorPorItem = scan.nextDouble();

        System.out.println("O valor total é de: R$" + (quantidadeItens * valorPorItem));
    }

    public void CalcularTroco( ) {
        System.out.println("Digite o valor recebido: ");
        double valorRecebido = scan.nextDouble();
        System.out.println("Digite o valor total da compra: ");
        double valorTotalCompra = scan.nextDouble();
        if(valorTotalCompra > valorRecebido) {
            System.out.println("Ainda faltam receber: R$" + (valorTotalCompra - valorRecebido));
            return;
        }

        if(valorTotalCompra == valorRecebido) System.out.println("Não há a necessidade de troco.");
        else
        System.out.println("O troco deverá ser de: R$" + (valorTotalCompra - valorRecebido));
    }

}
