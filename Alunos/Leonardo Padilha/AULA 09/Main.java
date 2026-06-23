import controller.CalculadoraController;
import model.CalculadoraModel;
import service.CalculadoraService;
import view.CalculadoraView;

public class Main {
    public static void main(String[] args) {
        CalculadoraView view = new CalculadoraView();
        CalculadoraModel model = new CalculadoraModel();
        CalculadoraService service = new CalculadoraService();

        new CalculadoraController(view, model, service);
    }
}