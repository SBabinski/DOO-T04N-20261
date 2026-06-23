package fag.objetos;

import java.util.Locale;

public class DadosClima {

    private final String cidade;
    private final double temperaturaAtual;
    private final double temperaturaMaxima;
    private final double temperaturaMinima;
    private final double umidade;
    private final String condicao;
    private final double precipitacao;
    private final double velocidadeVento;
    private final double direcaoVento;

    public DadosClima(
            String cidade,
            double temperaturaAtual,
            double temperaturaMaxima,
            double temperaturaMinima,
            double umidade,
            String condicao,
            double precipitacao,
            double velocidadeVento,
            double direcaoVento) {

        this.cidade = formatarEndereco(cidade);
        this.temperaturaAtual = temperaturaAtual;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.umidade = umidade;
        this.condicao = formatarTexto(condicao);
        this.precipitacao = precipitacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
    }

    public String getCidade() {
        return cidade;
    }

    public double getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public double getUmidade() {
        return umidade;
    }

    public String getCondicao() {
        return condicao;
    }

    public double getPrecipitacao() {
        return precipitacao;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public double getDirecaoVento() {
        return direcaoVento;
    }

    public String direcaoVentoTexto() {
        if (direcaoVento > 337.5 || direcaoVento <= 22.5) {
            return "Norte";
        } else if (direcaoVento > 22.5 && direcaoVento <= 67.5) {
            return "Nordeste";
        } else if (direcaoVento > 67.5 && direcaoVento <= 112.5) {
            return "Leste";
        } else if (direcaoVento > 112.5 && direcaoVento <= 157.5) {
            return "Sudeste";
        } else if (direcaoVento > 157.5 && direcaoVento <= 202.5) {
            return "Sul";
        } else if (direcaoVento > 202.5 && direcaoVento <= 247.5) {
            return "Sudoeste";
        } else if (direcaoVento > 247.5 && direcaoVento <= 292.5) {
            return "Oeste";
        } else if (direcaoVento > 292.5 && direcaoVento <= 337.5) {
            return "Noroeste";
        }

        return "Nao informado";
    }

    public Object[] paraLinhaTabela() {
        return new Object[] {
                cidade,
                formatarTemperatura(temperaturaAtual),
                formatarTemperatura(temperaturaMinima),
                formatarTemperatura(temperaturaMaxima),
                formatarPercentual(umidade),
                condicao,
                formatarMilimetros(precipitacao),
                direcaoVentoTexto(),
                formatarVelocidade(velocidadeVento)
        };
    }

    public String resumo() {
        return "Cidade: " + cidade
                + ", Temperatura atual: " + formatarTemperatura(temperaturaAtual)
                + ", Maxima do dia: " + formatarTemperatura(temperaturaMaxima)
                + ", Minima do dia: " + formatarTemperatura(temperaturaMinima)
                + ", Umidade: " + formatarPercentual(umidade)
                + ", Condicao: " + condicao
                + ", Precipitacao: " + formatarMilimetros(precipitacao)
                + ", Vento: " + formatarVelocidade(velocidadeVento)
                + ", Direcao: " + direcaoVentoTexto();
    }

    private String formatarTemperatura(double valor) {
        return String.format("%.1f C", valor);
    }

    private String formatarPercentual(double valor) {
        return String.format("%.0f%%", valor);
    }

    private String formatarMilimetros(double valor) {
        return String.format("%.1f mm", valor);
    }

    private String formatarVelocidade(double valor) {
        return String.format("%.1f km/h", valor);
    }

    private String formatarEndereco(String texto) {
        if (texto == null || texto.isBlank()) {
            return "Nao informado";
        }

        String[] partes = texto.split(",");
        StringBuilder endereco = new StringBuilder();

        for (int i = 0; i < partes.length; i++) {
            if (i > 0) {
                endereco.append(", ");
            }

            endereco.append(formatarParteEndereco(partes[i].trim()));
        }

        return endereco.toString();
    }

    private String formatarParteEndereco(String texto) {
        if (texto.length() == 2) {
            return texto.toUpperCase(Locale.of("pt", "BR"));
        }

        return formatarTexto(texto);
    }

    private String formatarTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            return "Nao informado";
        }

        String textoMinusculo = texto.toLowerCase(Locale.of("pt", "BR"));
        StringBuilder resultado = new StringBuilder();
        boolean proximaMaiuscula = true;

        for (int i = 0; i < textoMinusculo.length(); i++) {
            char caractere = textoMinusculo.charAt(i);

            if (Character.isLetter(caractere) && proximaMaiuscula) {
                resultado.append(Character.toTitleCase(caractere));
                proximaMaiuscula = false;
            } else {
                resultado.append(caractere);
            }

            if (caractere == ' ' || caractere == '-' || caractere == '\'') {
                proximaMaiuscula = true;
            }
        }

        return resultado.toString();
    }
}
