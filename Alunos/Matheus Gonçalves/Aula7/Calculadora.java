package fag;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calculadora {
    static Scanner scan = new Scanner(System.in); 
    static ArrayList<Venda> RegistroVendas = new ArrayList<>();
    static Loja loja = null;

    public static void main(String args[]) {
        Menu();
    }
    
    public static void Menu() {
        System.out.println("-----------------------------------");
        System.out.println("Escolha a Operação");
        System.out.println("[1]  - Calcular Preço Total");
        System.out.println("[2]  - Calcular Troco");
        System.out.println("[3]  - Registro de Vendas");
        System.out.println("[4]  - Pesquisar pela Data");
        System.out.println("[5]  - Criar Loja");
        System.out.println("[6]  - Criar Vendedor");
        System.out.println("[7]  - Criar Cliente");
        System.out.println("[8]  - Mostrar Loja");
        System.out.println("[9]  - Mostrar Cliente");
        System.out.println("[10] - Mostrar Vendedor");
        System.out.println("[11] - Ver a Média Salárial de um Vendedor");
        System.out.println("[12] - Ver o Bônus Salárial de um Vendedor");
        System.out.println("[13] - Criar Gerente");
        System.out.println("[14] - Criar Item");
        System.out.println("[15] - Processar Pedido");
        System.out.println("[16] - Sair");
        
        int escolha = scan.nextInt();
        
        switch (escolha) {
            case 1:
                PrecoTotal();
            break;
            
            case 2:
                Troco();
            break;
            
            case 3: 
                Registro();
            break;
            
            case 4: 
                PesquisaData();
            break;
            
            case 5:
                CriarLoja();
            break;
            
            case 6:
                CriarVendedor();
            break;
            
            case 7:
                CriarCliente();
            break;
            
            case 8:
                loja.apresentarse();
            break;
            
            case 9:
            	MostrarCliente();
            break;
            
            case 10:
            	MostrarVendedores();
            break;
            
            case 11:
            	MediaVendedores();
            break;
            
            case 12:
            	BonusVendedores();
            break;

            case 13:
                CriarGerente();
            break;

            case 14:
                CriarItem();
            break;

            case 15:
                ProcessarPedidoMenu();
            break;

            case 16:
                System.out.println("Você saiu do sistema...");
                return;

            default:
                System.out.println("Input invalido");
            break;
        }
        Menu();
    }
    
    public static void PrecoTotal() {
        System.out.println("Qual a quantidade do item ?");
        double qtd = scan.nextDouble();
        if (qtd <= 0) {
            System.out.println("quantidade invalida");
            return;
        }
        
        System.out.println("Qual o valor unitario? ");
        double vlrunt = scan.nextDouble();
        if (vlrunt <= 0) {
            System.out.println("valor invalida");
            return;
        }
        
        double desconto = 0.0;
        double total = qtd * vlrunt;

        if (qtd >= 10) {
            System.out.println("Desconto especial aplicado para mais de 10 itens: 5%");
            desconto = total * 0.05;
            total = total - desconto;
        }
        
        System.out.println("o Total é " + total);
        
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Venda venda = new Venda(hoje.format(formato), total);
        RegistroVendas.add(venda);
    }
    
    public static void Troco() {
        System.out.println("Qual o valor pago pelo cliente ?");
        double vlrPago = scan.nextDouble();
        if (vlrPago <= 0) {
            System.out.println("valor invalido");
            return;
        }
        
        System.out.println("Qual o total da compra ?");
        double vlrTotal = scan.nextDouble();
        if (vlrTotal <= 0) {
            System.out.println("valor invalido");
            return;
        }
        
        double troco = vlrPago - vlrTotal;

        if (troco < 0) {
            System.out.println("Faltam " + (troco * -1));
        } else {
            System.out.println("o Troco é de R$" + troco);
        }
    }
    
    public static void Registro() {
        for (int i = 0; i < RegistroVendas.size(); i++) {
            Venda v = RegistroVendas.get(i);
            System.out.println("[" + i + "] Data: " + v.getData() + " | Total: " + v.getValor());
        }
    }
    
    public static void PesquisaData() {
        scan.nextLine(); 

        System.out.println("Digite a data (dd/MM/yyyy): ");
        String dataBusca = scan.nextLine();

        int quantidade = 0;
        double soma = 0;

        for (int i = 0; i < RegistroVendas.size(); i++) {
            Venda v = RegistroVendas.get(i);

            if (v.getData().equals(dataBusca)) {
                quantidade++;
                soma += v.getValor();
            }
        }

        System.out.println("Data: " + dataBusca);
        System.out.println("Quantidade de vendas: " + quantidade);
        System.out.println("Total faturado: R$ " + soma);
    }
    
    public static void CriarLoja() {
        loja = new Loja();

        scan.nextLine();
        System.out.println("Nome Fantasia:");
        loja.setNomeFantasia(scan.nextLine());

        System.out.println("Razão Social:");
        loja.setRazaoSocial(scan.nextLine());

        System.out.println("CNPJ:");
        loja.setCnpj(scan.nextLine());

        Endereco end = new Endereco();

        System.out.println("Estado:");
        end.setEstado(scan.nextLine());

        System.out.println("Cidade:");
        end.setCidade(scan.nextLine());

        System.out.println("Bairro:");
        end.setBairro(scan.nextLine());

        System.out.println("Número:");
        end.setNumero(scan.nextInt());
        scan.nextLine();

        System.out.println("Complemento:");
        end.setComplemento(scan.nextLine());

        loja.setEndereco(end);

        if (loja.getNomeFantasia().isBlank() || loja.getRazaoSocial().isBlank() || loja.getCnpj().isBlank()
            || loja.getEndereco() == null) {
            System.out.println("Falha valores invalidos");
            return;
        }

        System.out.println("Loja criada com sucesso!");
    }
    
    public static void CriarVendedor() {
        if (loja == null) {
            System.out.println("Crie uma loja primeiro!");
            return;
        }

        Vendedor vendedor = new Vendedor();

        scan.nextLine();
        System.out.println("Nome:");
        vendedor.setNome(scan.nextLine());

        System.out.println("Idade:");
        vendedor.setIdade(scan.nextInt());
        scan.nextLine();

        Endereco end = new Endereco();

        System.out.println("Estado:");
        end.setEstado(scan.nextLine());

        System.out.println("Cidade:");
        end.setCidade(scan.nextLine());

        System.out.println("Bairro:");
        end.setBairro(scan.nextLine());

        System.out.println("Número:");
        end.setNumero(scan.nextInt());
        scan.nextLine();

        System.out.println("Complemento:");
        end.setComplemento(scan.nextLine());

        vendedor.setEndereco(end);

        System.out.println("Salário Base:");
        vendedor.setSalarioBase(scan.nextDouble());

        if (vendedor.getNome() == null || vendedor.getNome().isBlank()
            || vendedor.getIdade() <= 0
            || vendedor.getSalarioBase() <= 0.0
            || vendedor.getEndereco() == null) {

            System.out.println("Falha valores invalidos");
            return;
        }

        vendedor.setLoja(loja);
        loja.adicionarVendedor(vendedor);

        System.out.println("Vendedor criado com sucesso!");
    }
    
    public static void CriarCliente() {
        if (loja == null) {
            System.out.println("Crie uma loja primeiro!");
            return;
        }

        Cliente cliente = new Cliente();

        scan.nextLine();
        System.out.println("Nome:");
        cliente.setNome(scan.nextLine());

        System.out.println("Idade:");
        cliente.setIdade(scan.nextInt());
        scan.nextLine();

        Endereco end = new Endereco();

        System.out.println("Estado:");
        end.setEstado(scan.nextLine());

        System.out.println("Cidade:");
        end.setCidade(scan.nextLine());

        System.out.println("Bairro:");
        end.setBairro(scan.nextLine());

        System.out.println("Número:");
        end.setNumero(scan.nextInt());
        scan.nextLine();

        System.out.println("Complemento:");
        end.setComplemento(scan.nextLine());

        cliente.setEndereco(end);

        if (cliente.getNome() == null || cliente.getNome().isBlank()
            || cliente.getIdade() <= 0
            || cliente.getEndereco() == null) {

            System.out.println("Falha valores invalidos");
            return;
        }

        loja.adicionarCliente(cliente);

        System.out.println("Cliente criado com sucesso!");
    }
    
    public static void MediaVendedores() {
        if (loja == null || loja.getVendedores().isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado!");
            return;
        }

        for (int i = 0; i < loja.getVendedores().size(); i++) {
            System.out.println("[" + i + "] " + loja.getVendedores().get(i).getNome());
        }

        System.out.println("Escolha o vendedor:");
        int op = scan.nextInt();

        if (op >= 0 && op < loja.getVendedores().size()) {
            Vendedor vend = loja.getVendedores().get(op);
            double[] salarios = new double[3];

            System.out.println("Digite o salario do mês 1:");
            salarios[0] = scan.nextDouble();

            System.out.println("Digite o salario do mês 2:");
            salarios[1] = scan.nextDouble();

            System.out.println("Digite o salario do mês 3:");
            salarios[2] = scan.nextDouble();

            vend.setSalarioRecebido(salarios);
            vend.calcularMedia();
        } else {
            System.out.println("Opção inválida");
        }
    }
    
    public static void BonusVendedores() {
        if (loja == null || loja.getVendedores().isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado!");
            return;
        }

        for (int i = 0; i < loja.getVendedores().size(); i++) {
            System.out.println("[" + i + "] " + loja.getVendedores().get(i).getNome());
        }

        System.out.println("Escolha o vendedor:");
        int op = scan.nextInt();

        if (op >= 0 && op < loja.getVendedores().size()) {
        	Vendedor vend = loja.getVendedores().get(op);
            vend.calcularBonus();
        } else {
            System.out.println("Opção inválida");
        }
    }
    
    public static void MostrarVendedores() {
        if (loja == null || loja.getVendedores().isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado!");
            return;
        }

        for (int i = 0; i < loja.getVendedores().size(); i++) {
            System.out.println("[" + i + "] " + loja.getVendedores().get(i).getNome());
        }

        System.out.println("Escolha o vendedor:");
        int op = scan.nextInt();

        if (op >= 0 && op < loja.getVendedores().size()) {
            loja.getVendedores().get(op).apresentarse();
        } else {
            System.out.println("Opção inválida");
        }
    }

    public static void MostrarCliente() {
        if (loja == null || loja.getClientes().isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }

        for (int i = 0; i < loja.getClientes().size(); i++) {
            System.out.println("[" + i + "] " + loja.getClientes().get(i).getNome());
        }

        System.out.println("Escolha o cliente:");
        int op = scan.nextInt();

        if (op >= 0 && op < loja.getClientes().size()) {
            loja.getClientes().get(op).apresentarse();
        } else {
            System.out.println("Opção inválida");
        }
    }
    
    public static void CriarGerente() {
        if (loja == null) {
            System.out.println("Crie uma loja primeiro!");
            return;
        }

        Gerente gerente = new Gerente();

        scan.nextLine();
        System.out.println("Nome:");
        gerente.setNome(scan.nextLine());

        System.out.println("Idade:");
        gerente.setIdade(scan.nextInt());
        scan.nextLine();

        Endereco end = new Endereco();

        System.out.println("Estado:");
        end.setEstado(scan.nextLine());

        System.out.println("Cidade:");
        end.setCidade(scan.nextLine());

        System.out.println("Bairro:");
        end.setBairro(scan.nextLine());

        System.out.println("Número:");
        end.setNumero(scan.nextInt());
        scan.nextLine();

        System.out.println("Complemento:");
        end.setComplemento(scan.nextLine());

        gerente.setEndereco(end);

        System.out.println("Salário Base:");
        gerente.setSalarioBase(scan.nextDouble());

        gerente.setLoja(loja);

        System.out.println("Gerente criado com sucesso!");
    }
    
    public static Item CriarItem() {
        Item item = new Item();

        System.out.println("ID:");
        item.setId(scan.nextInt());
        scan.nextLine();

        System.out.println("Nome:");
        item.setNome(scan.nextLine());

        System.out.println("Tipo:");
        item.setTipo(scan.nextLine());

        System.out.println("Valor:");
        item.setValor(scan.nextDouble());

        System.out.println("Item criado!");
        return item;
    }
    
    public static void ProcessarPedidoMenu() {
    	ProcessarPedido pedido = CriarPedidoFake();
    	
        if (pedido != null) {
            pedido.gerarDescricaoVenda();
        }
    }
    
    public static ProcessarPedido CriarPedidoFake() {
        if (loja == null || loja.getClientes().isEmpty() || loja.getVendedores().isEmpty()) {
            System.out.println("Precisa ter loja, cliente e vendedor!");
            return null;
        }

        Cliente cliente = loja.getClientes().get(0);
        Vendedor vendedor = loja.getVendedores().get(0);

        ProcessarPedido pedido = new ProcessarPedido();
        pedido.processar(cliente, vendedor, loja);

        Item item = new Item();
        item.setId(1);
        item.setNome("Produto Teste");
        item.setTipo("Geral");
        item.setValor(100);

        pedido.adicionarItem(item);

        System.out.println("Pedido fake criado!");
        return pedido;
    }
}