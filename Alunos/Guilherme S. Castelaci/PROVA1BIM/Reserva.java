public class Reserva {
    Hospede hospede;
    Quarto quarto;
    int in, out;
    boolean realizado;

    public Reserva(Hospede h, Quarto q, int i, int o) {
        hospede = h;
        quarto = q;
        in = i;
        out = o;
        realizado = false;
    }

    public void realizarCheckout() {
        realizado = true;
    }

    public double total() {
        return (out - in) * quarto.valor;
    }

    public void exibir() {
        System.out.println(hospede.nome + " Quarto " + quarto.numero + " Total " + total());
    }
}