package climatempo.services;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaClima {

    @JsonProperty("resolvedAddress")
    private String endereco;

    @JsonProperty("currentConditions")
    private CondicoesAtuais condicoesAtuais;

    @JsonProperty("days")
    private List<Dia> dias;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public CondicoesAtuais getCondicoesAtuais() {
        return condicoesAtuais;
    }

    public void setCondicoesAtuais(CondicoesAtuais condicoesAtuais) {
        this.condicoesAtuais = condicoesAtuais;
    }

    public List<Dia> getDias() {
        return dias;
    }

    public void setDias(List<Dia> dias) {
        this.dias = dias;
    }
}
