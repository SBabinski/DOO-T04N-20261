public class Cliente extends Pessoa {
    @Override
    public void apresentarSe() {
        System.out.println("Cliente: " + nome + " | Idade: " + idade + " anos.");
    }
}