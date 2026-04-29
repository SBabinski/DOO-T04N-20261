public class Reserva {

    private Hospede hospede;       
    private Quarto quarto;      
    private String dataCheckIn; 
    private String dataCheckOut; 
    private boolean checkOutRealizado; 
    private int quantidadeDiarias; 

 
    public Reserva(Hospede hospede, Quarto quarto, String dataCheckIn, String dataCheckOut, int quantidadeDiarias) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.quantidadeDiarias = quantidadeDiarias;
        this.checkOutRealizado = false; 
    }

    public Hospede getHospede() {
        return hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public boolean isCheckOutRealizado() {
        return checkOutRealizado;
    }

    public void realizarCheckOut() {
        this.checkOutRealizado = true;
        System.out.println("Check-out realizado com sucesso para " + hospede.getNome() + "!");
    }

    public double calcularValorTotal() {
      
        return quantidadeDiarias * quarto.getValorDiaria();
    }
    
    public void exibirDados() {
        System.out.println("==============================");
        System.out.println(" DADOS DA RESERVA HOTEL TESK  ");
        System.out.println("==============================");

    
        hospede.exibirDados();

        System.out.println();

        quarto.exibirInformacoes();

        System.out.println();
        System.out.println("Check-in: " + dataCheckIn);
        System.out.println("Check-out previsto: " + dataCheckOut);
        System.out.println("Quantidade de diárias: " + quantidadeDiarias);
        System.out.printf("Valor total: R$ %.2f%n", calcularValorTotal()); 
        System.out.println("Situação: " + (checkOutRealizado ? "Check-out realizado" : "Ativo (sem check-out)"));
        System.out.println("==============================");
    }
}
