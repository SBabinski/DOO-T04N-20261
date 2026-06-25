import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private User user;
    private Quarto quarto;
    private LocalDate chegada;
    private LocalDate saida;
    private boolean saidaRealizada;

    public Reserva(User user, Quarto quarto, LocalDate chegada, LocalDate saida) {
        this.user = user;
        this.quarto = quarto;
        this.chegada = chegada;
        this.saida = saida;
        this.saidaRealizada = false;
    }
    public User getUser() {
        return user;
    }
    public Quarto getQuarto() {
        return quarto;
    }
    public LocalDate getChegada() {
        return chegada;
    }
    public LocalDate getSaida() {
        return saida;
    }
    public boolean isSaidaRealizada() {
        return saidaRealizada;
    }
    public void realizarSaida() {
        this.saidaRealizada = true;
    }
    public long calcularDias() {
        return ChronoUnit.DAYS.between(chegada, saida);
    }
    public double calcularValorTotal() {
        long dias = ChronoUnit.DAYS.between(chegada, saida);
        return dias * quarto.getValorDiaria();
    }
    
    public void mostrarResumo() {
        System.out.println("=== Consulta de Reserva ===");
        user.mostrarResumo();
        quarto.mostrarResumo();
        System.out.println("Data de chegada: " + chegada);
        System.out.println("Data de saída: " + saida);
        System.out.println("Dias de estadia: " + calcularDias());
        System.out.println("Valor total: R$ " + calcularValorTotal());
        System.out.println("Saída realizada: " + (saidaRealizada ? "Sim" : "Não"));
    }
}