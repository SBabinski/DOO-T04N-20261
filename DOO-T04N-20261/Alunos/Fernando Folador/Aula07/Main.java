import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Endereco end = new Endereco();
        end.estado = "PR";
        end.cidade = "Juvinópolis-Cascavel";
        end.bairro = "Centro";
        end.numero = "200";
        end.complemento = "Casa";

        Loja loja = new Loja();
        loja.nomeFantasia = "My Plant";
        loja.cnpj = "123456789";
        loja.cidade = "Cascavel";
        loja.bairro = "Centro";
        loja.rua = "Avenida Brasil";

        Vendedor vendedor = new Vendedor();
        vendedor.nome = "Fernando";
        vendedor.idade = 18;
        vendedor.loja = "My Plant";
        vendedor.salarioBase = 2000;
        vendedor.endereco = end;

        vendedor.salarioRecebido.add(2000.0);
        vendedor.salarioRecebido.add(2200.0);
        vendedor.salarioRecebido.add(2100.0);

        loja.vendedores.add(vendedor);

        Cliente cliente = new Cliente();
        cliente.nome = "João";
        cliente.idade = 28;
        cliente.endereco = end;

        loja.clientes.add(cliente);

        Item planta1 = new Item();
        planta1.id = 1;
        planta1.nome = "Samambaia";
        planta1.tipo = "Planta ornamental";
        planta1.valor = 50;

        Item planta2 = new Item();
        planta2.id = 2;
        planta2.nome = "Cacto";
        planta2.tipo = "Planta desértica";
        planta2.valor = 30;

        Pedido pedido = new Pedido();
        pedido.id = 1;
        pedido.dataCriacao = new Date();
        pedido.dataVencimentoReserva = new Date(System.currentTimeMillis() + 86400000); // +1 dia
        pedido.cliente = cliente;
        pedido.vendedor = vendedor;
        pedido.loja = loja;

        pedido.itens.add(planta1);
        pedido.itens.add(planta2);

        ProcessaPedido processa = new ProcessaPedido();
        processa.processar(pedido);

        pedido.gerarDescricaoVenda();
    }
}