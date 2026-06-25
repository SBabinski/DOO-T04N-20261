package principal.classes;
import java.time.LocalDate;

public class Check_in {
    public Hospede hospede;
    public quarto Quarto;
    public LocalDate dataInicio;
    public int prazo;
    public boolean devolvido;

    public Check_in(Hospede hospede, quarto Quarto, int prazo) {
        this.hospede = hospede;
        this.Quarto = Quarto;
        this.dataInicio = LocalDate.now();
        this.prazo = prazo;
        this.devolvido = false;
    }

    public void exibirCu() {
        double valorTotal = prazo * Quarto.getValor();
        System.out.println("\nHospede: " + hospede.Nome);
        Quarto.mostrarDados();
        System.out.println("\nPrazo: " + prazo + " dias | Total: R$ " + valorTotal);
        System.out.println("Status: " + (devolvido ? "FINALIZADO" : "ATIVO"));
    }
}