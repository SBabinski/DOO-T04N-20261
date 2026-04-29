import java.time.LocalDate;

 class Venda {

    double valunid;
    int quant;
    LocalDate dataVenda;

    public Venda(int quant, double valunid) {
        this.quant = quant;
        this.valunid = valunid;
        this.dataVenda = LocalDate.now();
    }

    public int getQuant(){
        return quant;
    }
    public double getValunid(){
        return valunid;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public double getValorFinal(){
        double total = valunid*quant;
        return(quant>10) ? total * 0.95 : total;
    }

}