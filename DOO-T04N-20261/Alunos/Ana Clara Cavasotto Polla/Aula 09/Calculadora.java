package fag;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculadora {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Calculadora");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		JPanel painelCampos = new JPanel();
		painelCampos.setBackground(Color.WHITE);

		JTextField numero1 = new JTextField(10);
		JTextField numero2 = new JTextField(10);

		JLabel textoNumero1 = new JLabel("Numero 1:");
		JLabel textoNumero2 = new JLabel("Numero 2:");

		painelCampos.add(textoNumero1);
		painelCampos.add(numero1);
		painelCampos.add(textoNumero2);
		painelCampos.add(numero2);

		JLabel resultado = new JLabel("Resultado: ");
		resultado.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel painelBotoes = new JPanel();
		painelBotoes.setBackground(Color.WHITE);

		JButton soma = new JButton("+");
		JButton subtracao = new JButton("-");
		JButton multiplicacao = new JButton("x");
		JButton divisao = new JButton("/");

		soma.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (numero1.getText().equals("") || numero2.getText().equals("")) {
						throw new CalculadoraException("Preencha os dois campos antes de calcular.");
					}

					double n1 = Double.parseDouble(numero1.getText());
					double n2 = Double.parseDouble(numero2.getText());
					double resposta = n1 + n2;
					resultado.setText("Resultado: " + resposta);
				} catch (CalculadoraException erro) {
					JOptionPane.showMessageDialog(frame,
							erro.getMessage(),
							"Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(frame,
							"Entrada invalida. Digite apenas numeros nos campos.",
							"Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		subtracao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (numero1.getText().equals("") || numero2.getText().equals("")) {
						throw new CalculadoraException("Preencha os dois campos antes de calcular.");
					}

					double n1 = Double.parseDouble(numero1.getText());
					double n2 = Double.parseDouble(numero2.getText());
					double resposta = n1 - n2;
					resultado.setText("Resultado: " + resposta);
				} catch (CalculadoraException erro) {
					JOptionPane.showMessageDialog(frame,
							erro.getMessage(),
							"Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(frame,
							"Entrada invalida. Digite apenas numeros nos campos.",
							"Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		multiplicacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (numero1.getText().equals("") || numero2.getText().equals("")) {
						throw new CalculadoraException("Preencha os dois campos antes de calcular.");
					}

					double n1 = Double.parseDouble(numero1.getText());
					double n2 = Double.parseDouble(numero2.getText());
					double resposta = n1 * n2;
					resultado.setText("Resultado: " + resposta);
				} catch (CalculadoraException erro) {
					JOptionPane.showMessageDialog(frame,
							erro.getMessage(),
							"Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(frame,
							"Entrada invalida. Digite apenas numeros nos campos.",
							"Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		divisao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (numero1.getText().equals("") || numero2.getText().equals("")) {
						throw new CalculadoraException("Preencha os dois campos antes de calcular.");
					}

					double n1 = Double.parseDouble(numero1.getText());
					double n2 = Double.parseDouble(numero2.getText());

					if (n2 == 0) {
						throw new CalculadoraException("Nao e possivel dividir por zero. Digite um segundo numero diferente de 0.");
					}

					double resposta = n1 / n2;
					resultado.setText("Resultado: " + resposta);
				} catch (CalculadoraException erro) {
					JOptionPane.showMessageDialog(frame,
							erro.getMessage(),
							"Erro",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(frame,
							"Entrada invalida. Digite apenas numeros nos campos.",
							"Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		painelBotoes.add(soma);
		painelBotoes.add(subtracao);
		painelBotoes.add(multiplicacao);
		painelBotoes.add(divisao);

		panel.add(painelCampos, BorderLayout.NORTH);
		panel.add(resultado, BorderLayout.CENTER);
		panel.add(painelBotoes, BorderLayout.SOUTH);

		frame.add(panel);
		frame.setVisible(true);
	}

	static class CalculadoraException extends RuntimeException {

		public CalculadoraException(String mensagem) {
			super(mensagem);
		}
	}
}
