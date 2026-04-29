public class User {
    private String nome;
    private String telefone;
    private String cpf;

    public User(String nome, String telefone, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }
    public void mostrarResumo() {
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
        System.out.println("CPF: " + cpf);
    }
        public String getNome() {
            return nome;
        }
        public String getTelefone() {
            return telefone;
        }
        public String getCpf() {
            return cpf;
        }
}