package classesAtividade;
import java.time.LocalDate;
public class CalculadoraVendas {

    public ResultadoVenda calcularVendasResposta(int valor01, double valor02, LocalDate data) {
        double resposta = (valor01 * valor02);
        double desconto = 0;

        if (valor01 >= 10) {
            desconto = resposta * 0.10;
            resposta = resposta - desconto;
        }

        return new ResultadoVenda(resposta, desconto, data, valor01);
    }

    public class ResultadoVenda {
        public double resposta;
        public double desconto;
        public LocalDate data;
		private int quantidade;

        public ResultadoVenda(double resposta, double desconto, LocalDate data, int quantidade) {
            this.resposta = resposta;
            this.desconto = desconto;
            this.data = data;
            this.quantidade = quantidade;
        }
        
        public String toString() {
            return "Quantidade: " + quantidade +
                   ", Valor final: " + resposta +
                   ", Desconto: " + desconto +
                   ", Data: " + data;
        }

		public LocalDate getData() {
			// TODO Auto-generated method stub
			return data;
		}
    }
}
