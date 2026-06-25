public class Vendedor {
    private String nome;
    private int idade;
    private String loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private double[] salariosRecebidos; // armazena pelo menos três lançamentos

    // Construtor principal: inicializa com três lançamentos de salário exemplo
    public Vendedor(String nome, int idade, String loja, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.salarioBase = salarioBase;
        // Valores de exemplo - pode alterar conforme necessário
        this.salariosRecebidos = new double[] {1500.0, 1600.0, 1550.0};
    }

    // Construtor auxiliar (mantém compatibilidade com código anterior)
    public Vendedor(String nome, int idade) {
        this(nome, idade, "Loja 1", 1500.0);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double[] getSalariosRecebidos() {
        return salariosRecebidos;
    }

    public void setSalariosRecebidos(double[] salariosRecebidos) {
        if (salariosRecebidos != null && salariosRecebidos.length >= 3) {
            this.salariosRecebidos = salariosRecebidos;
        } else {
            System.out.println("salariosRecebidos deve conter pelo menos 3 valores.");
        }
    }

    // Método apresentarse: imprime nome, idade e loja
    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
    }

    // calcularMedia: retorna a média dos salários armazenados em salariosRecebidos
    public double calcularMedia() {
        if (salariosRecebidos == null || salariosRecebidos.length == 0) return 0.0;
        double soma = 0.0;
        for (double s : salariosRecebidos) soma += s;
        return soma / salariosRecebidos.length;
    }

    // calcularBonus: fórmula salarioBase * 0.2
    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}