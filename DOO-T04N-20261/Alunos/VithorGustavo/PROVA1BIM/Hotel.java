
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
    private List<Hospede> hospedes;
    private List<Quarto> quartos;
    private List<Reserva> reservas;

    public Hotel() {
        this.hospedes = new ArrayList<>();
        this.quartos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void adicionarHospede(Hospede hospede) {
        hospedes.add(hospede);
    }

    public void adicionarQuarto(Quarto quarto) {
        quartos.add(quarto);
    }

    public boolean adicionarReserva(Reserva reserva) {
        if (reservas.size() >= 10) {
            return false;
        }
        reservas.add(reserva);
        return true;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public List<Reserva> listarReservasAtivas() {
        return reservas.stream().filter(reserva -> !reserva.isCheckoutRealizado()).collect(Collectors.toList());
    }

    public Hospede buscarHospedePorCpf(String cpf) {
        for (Hospede hospede : hospedes) {
            if (hospede.getCpf().equals(cpf)) {
                return hospede;
            }
        }
        return null;
    }

    public Quarto buscarQuartoPorNumero(int numero) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numero) {
                return quarto;
            }
        }
        return null;
    }
}
