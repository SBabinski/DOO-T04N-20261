package provaBimestral;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    int numero;
    String nome;
    LocalDate dataEntrada;
    LocalDate dataSaida;
    double valorDiaria;

    public Reserva(Object object, String nome, LocalDate dataEntrada, LocalDate dataSaida, double valorDiaria) {
        this.numero = (int) object;
        this.nome = nome;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valorDiaria = valorDiaria;
    }

    public void exibirInformacoes() {
        long quantidadeDiarias = ChronoUnit.DAYS.between(dataEntrada, dataSaida);
        double valorTotal = quantidadeDiarias * valorDiaria;

        System.out.println("===== RESERVA =====");
        System.out.println("Quarto: " + numero);
        System.out.println("Hóspede: " + nome);
        System.out.println("Entrada: " + dataEntrada);
        System.out.println("Saída: " + dataSaida);
        System.out.println("Diárias: " + quantidadeDiarias);
        System.out.println("Valor da diária: R$ " + valorDiaria);
        System.out.println("Total a pagar: R$ " + valorTotal);
        System.out.println("===================");
    }
}