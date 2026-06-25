package app;

import java.util.Scanner;

import model.Clima;
import service.ClimaService;

public class Main {

    public static void main(String[] args) {

        Scanner scanner =
                new Scanner(System.in);

        ClimaService service =
                new ClimaService();

        int opcao = -1;

        while (opcao != 0) {

            System.out.println(
                    "\n===== SISTEMA CLIMÁTICO =====");

            System.out.println(
                    "1 - Consultar clima");

            System.out.println(
                    "0 - Encerrar sistema");

            System.out.print(
                    "\nDigite uma opção: ");

            if (!scanner.hasNextInt()) {

                System.out.println(
                        "\nOpção inválida, tente novamente.");

                scanner.nextLine();

                continue;
            }

            opcao = scanner.nextInt();

            scanner.nextLine();

            switch (opcao) {

                case 1:

                    int opcaoConsulta = -1;

                    while (opcaoConsulta != 0
                            && opcaoConsulta != 1) {

                        System.out.print(
                                "\nDigite o nome da cidade: ");

                        String cidade =
                                scanner.nextLine();

                        Clima clima =
                                service.buscarClima(cidade);

                        if (!clima.isCidadeEncontrada()) {

                            System.out.println(
                                    "\nNenhuma cidade encontrada.");

                            System.out.println(
                                    "Tente novamente digitando 2.");

                            System.out.println(
                                    "\n1 - Voltar ao menu");

                            System.out.println(
                                    "2 - Consultar outra cidade");

                            System.out.println(
                                    "0 - Encerrar sistema");

                            System.out.print(
                                    "\nDigite uma opção: ");

                            if (!scanner.hasNextInt()) {

                                System.out.println(
                                        "\nOpção inválida, tente novamente.");

                                scanner.nextLine();

                                continue;
                            }

                            opcaoConsulta =
                                    scanner.nextInt();

                            scanner.nextLine();

                            switch (opcaoConsulta) {

                                case 1:

                                    System.out.println(
                                            "\nRetornando ao menu...");

                                    break;

                                case 2:

                                    opcaoConsulta = -1;

                                    break;

                                case 0:

                                    opcao = 0;

                                    System.out.println(
                                            "\nSistema encerrado.");

                                    break;

                                default:

                                    System.out.println(
                                            "\nOpção inválida, tente novamente.");

                                    opcaoConsulta = -1;
                            }

                            continue;
                        }

                        System.out.println(
                                "\n===== DADOS CLIMÁTICOS =====");

                        System.out.println(
                                "Temperatura Atual: "
                                + clima.getTemperaturaAtual()
                                + " °C");

                        System.out.println(
                                "Temperatura Máxima: "
                                + clima.getTemperaturaMaxima()
                                + " °C");

                        System.out.println(
                                "Temperatura Mínima: "
                                + clima.getTemperaturaMinima()
                                + " °C");

                        System.out.println(
                                "Umidade do Ar: "
                                + clima.getUmidade()
                                + "%");

                        System.out.println(
                                "Condição do Tempo: "
                                + clima.getCondicaoTempo());

                        System.out.println(
                                "Precipitação: "
                                + clima.getPrecipitacao()
                                + " mm");

                        System.out.println(
                                "Velocidade do Vento: "
                                + clima.getVelocidadeVento()
                                + " km/h");

                        System.out.println(
                                "Direção do Vento: "
                                + clima.getDirecaoVento()
                                + "°");

                        System.out.println(
                                "\n1 - Voltar ao menu");

                        System.out.println(
                                "2 - Consultar outra cidade");

                        System.out.println(
                                "0 - Encerrar sistema");

                        System.out.print(
                                "\nDigite uma opção: ");

                        if (!scanner.hasNextInt()) {

                            System.out.println(
                                    "\nOpção inválida, tente novamente.");

                            scanner.nextLine();

                            continue;
                        }

                        opcaoConsulta =
                                scanner.nextInt();

                        scanner.nextLine();

                        switch (opcaoConsulta) {

                            case 1:

                                System.out.println(
                                        "\nRetornando ao menu...");

                                break;

                            case 2:

                                System.out.println(
                                        "\nNova consulta:");

                                opcaoConsulta = -1;

                                break;

                            case 0:

                                opcao = 0;

                                System.out.println(
                                        "\nSistema encerrado.");

                                break;

                            default:

                                System.out.println(
                                        "\nOpção inválida, tente novamente.");

                                opcaoConsulta = -1;
                        }
                    }

                    break;

                case 0:

                    System.out.println(
                            "\nSistema encerrado.");

                    break;

                default:

                    System.out.println(
                            "\nOpção inválida, tente novamente.");
            }
        }

        scanner.close();
    }
}