import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean checkOutRealizado;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate checkIn, LocalDate checkOut) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.checkOutRealizado = false;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public boolean isCheckOutRealizado() {
        return checkOutRealizado;
    }

    public void setCheckOutRealizado(boolean checkOutRealizado) {
        this.checkOutRealizado = checkOutRealizado;
    }

    public long getDias() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public double getValorTotal() {
        return getDias() * quarto.getValorDiaria();
    }

    @Override
    public String toString() {
        return " \nReserva{" +
                " hospede = " + hospede.getNome() +
                ", \n quarto = " + quarto.getNumero() +
                ", \n checkIn = " + checkIn +
                ",\n checkOut = " + checkOut +
                ", \n checkOutRealizado = " + checkOutRealizado +
                ", \n valorTotal = R$ " + getValorTotal() +
                '}';
    }
}