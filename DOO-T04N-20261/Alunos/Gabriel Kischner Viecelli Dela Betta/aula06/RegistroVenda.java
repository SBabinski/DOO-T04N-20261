import java.util.ArrayList;

public class RegistroVenda {

    ArrayList<itemVenda> registros = new ArrayList<>();

    public void registrarVenda(float valor) {
        registros.add(new itemVenda(valor));

    }
    
    public void exibirRegistros(Venda venda) {
        int i = 1;
    for (itemVenda item : registros) {
    
        System.out.println("Venda "+i+" : " + "R$"  + item.valor);
        i++;
    }
}

  

    public float buscarTotalPorData(int dia, int mes) {
    float soma = 0;
    int i = 0; 

    for (itemVenda item : registros) {
        if (item.data.getDayOfMonth() == dia && item.data.getMonthValue() == mes) {
            soma += item.valor;
            i++; 
        }
    }
    
    if (i > 0) {
        System.out.println("Foram encontradas " + i + " vendas neste dia.");
    }
    
    return soma;
}
}
