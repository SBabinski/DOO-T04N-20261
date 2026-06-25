package calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.util.List;

public class main extends RuntimeException {

	public static void main(String[] args) {
		JFrame tela = new JFrame("Calculadora");
		tela.setSize(340, 540);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setLayout(new BorderLayout());
		
		
		
		JPanel telinha = new JPanel();
		JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setLayout(new GridLayout(4, 4));
		telinha.setBackground(Color.black);
		
		JTextArea visor = new JTextArea();
		telinha.add(visor, BorderLayout.NORTH);
		telinha.setSize(340, 100);
		
		JButton num1 = new JButton("1");
		num1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "1");
			}
		});
		JButton num2 = new JButton("2");
		num2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "2");
			}
		});
		JButton num3 = new JButton("3");
		num3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "3");
			}
		});
		JButton num4 = new JButton("4");
		num4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "4");
			}
		});
		JButton num5 = new JButton("5");
		num5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "5");
			}
		});
		JButton num6 = new JButton("6");
		num6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "6");
			}
		});
		JButton num7 = new JButton("7");
		num7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "7");
			}
		});
		JButton num8 = new JButton("8");
		num8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "8");
			}
		});
		JButton num9 = new JButton("9");
		num9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "9");
			}
		});
		JButton num0 = new JButton("0");
		num0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "0");
			}
		});
		JButton sum = new JButton("+");
		sum.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "+");
			}
		});
		JButton sub = new JButton("-");
		sub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "-");
			}
		});
		JButton div = new JButton("/");
		div.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "/");
			}
		});
		JButton vs = new JButton("X");
		vs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + "*");
			}
		});
		JButton eq = new JButton("=");
		eq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String expressao = visor.getText();
				String[] partes = expressao.split("(?=[+\\-*/])|(?<=[+\\-*/])");
				System.out.println(partes.length);
				ArrayList<Float> nums = new ArrayList<>();
				ArrayList<String> oper = new ArrayList<>();
				try {
					for (int i=0; i < partes.length; i++) {
						if (i % 2 == 0) {
							nums.add(Float.parseFloat(partes[i]));
						} else if (i % 2 != 0){
							oper.add(partes[i]);
						}
					}
					float result = nums.get(0);
					try{
						for (int x = 0; x < oper.size(); x++) {
							result = Calcular(nums.get(x+1), oper.get(x), result);
						}
						if(!Double.isInfinite(result)) {
							visor.setText(String.valueOf(result));
						} else {
							throw new ArithmeticException();
						}
					} catch (ArithmeticException x) {
						BalaException("não é possivel fazer divisão por 0");
					}
				} catch (Exception x) {
					BalaException("apenas caracteres numericos e simbolos de operações matematicas");
				}
			}
		});
		JButton vir = new JButton(",");
		vir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String svis = visor.getText();
				visor.setText(svis + ".");
			}
		});
		
		panel.add(num1);
		panel.add(num2);
		panel.add(num3);
		panel.add(sum);
		panel.add(num4);
		panel.add(num5);
		panel.add(num6);
		panel.add(sub);
		panel.add(num7);
		panel.add(num8);
		panel.add(num9);
		panel.add(div);
		panel.add(vir);
		panel.add(num0);
		panel.add(vs);
		panel.add(eq);
		
		tela.add(telinha);
		tela.add(panel, BorderLayout.SOUTH);
		
		tela.setVisible(true);
	}
	
	public static float Calcular(float num1, String operador, float result) {
		switch(operador) {
		case "+":
			result += num1;
			System.out.println("esta somando");
			break;
		case "-":
			result -= num1;
			System.out.println("esta subtraindo");
			break;
		case "/":
			result /= num1;
			System.out.println("esta dividindo");
			break;
		case "*":
			result *= num1;
			System.out.println("esta multiplicando");
			break;
		default:
			System.out.println("para onde foi o operador????????");
		}
		return result;
	}
	
	public static void BalaException(String message) {
		JOptionPane.showMessageDialog(null, message, "Erro não esperado", JOptionPane.ERROR_MESSAGE);
	}

}
