package controller;

import exception.CalculadoraException;
import model.CalculadoraModel;
import service.CalculadoraService;
import view.CalculadoraView;

import javax.swing.*;

public class CalculadoraController {

    private final CalculadoraView view;
    private final CalculadoraModel model;
    private final CalculadoraService service;

    public CalculadoraController(CalculadoraView view, CalculadoraModel model, CalculadoraService service) {
        this.view = view;
        this.model = model;
        this.service = service;

        adicionarEventos();
    }

    private void adicionarEventos() {
        view.getSomaBotao().addActionListener(e -> calcular("+"));
        view.getSubtracaoBotao().addActionListener(e -> calcular("-"));
        view.getMultiplicacaoBotao().addActionListener(e -> calcular("*"));
        view.getDivisaoBotao().addActionListener(e -> calcular("/"));
    }

    private void calcular(String operacao) {
        try {
            double n1 = service.converterNumero(view.getPrimeiroNumero().getText());
            double n2 = service.converterNumero(view.getSegundoNumero().getText());

            model.setPrimeiroNumero(n1);
            model.setSegundoNumero(n2);

            double resultado = service.calcular(model, operacao);
            model.setResultado(resultado);

            view.getDisplay().setText("Resultado: " + resultado);

        } catch (CalculadoraException e) {
            JOptionPane.showMessageDialog(
                    view,
                    e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}