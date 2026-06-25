package gerenciarHotel;



	public class QuartoSimples extends Quarto {
	    private boolean ventilador;

	    public QuartoSimples(int numero, double valorDiaria, boolean ventilador) {
	        super(numero, valorDiaria);
	        this.ventilador = ventilador;
	    }

	
	    public void exibirInformacoes() {
	        System.out.println("Quarto Simples");
	        System.out.println("Número: " + numero);
	        System.out.println("Valor diária: " + valorDiaria);
	        System.out.println("Ventilador: " + (ventilador ? "Sim" : "Não"));
	    }
	}

	
	
	