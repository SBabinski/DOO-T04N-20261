import java.util.Date;

public class GerarDados {
    
    // O segredo e o parametro 'int c' (contador atual)
    public static int carregar(Hospedes[] h, Quartos[] q, Reserva[] r, int c) {
        
        // Verifica se ainda ha espaco no array de 10
        if (c <= 8) {
            // Registro 1 da Demo
            h[c] = new Hospedes("Ana Silva (Demo)", "111.111.111-11", "9999-9999");
            q[c] = new QuartoSimples(101, 150.0, true);
            r[c] = new Reserva(h[c], q[c], new Date(), new Date(new Date().getTime() + 86400000L));
            c++;

            // Registro 2 da Demo
            h[c] = new Hospedes("Bruno Costa (Demo)", "222.222.222-22", "8888-8888");
            q[c] = new QuartoLuxo(201, 400.0, true);
            r[c] = new Reserva(h[c], q[c], new Date(), new Date(new Date().getTime() + 172800000L));
            c++;
        } else {
            System.out.println("Aviso: Nao ha espaco suficiente para carregar a demo completa.");
        }

        return c; // Retorna o novo valor do contador para a Main
    }
}