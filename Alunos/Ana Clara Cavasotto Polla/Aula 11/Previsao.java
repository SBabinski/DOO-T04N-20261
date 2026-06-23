package fag.objetos;

import java.util.ArrayList;

public class Previsao {

    private String resolvedAddress;
    private CondicoesAtuais currentConditions;
    private ArrayList<DiaClima> days = new ArrayList<>();

    public String getResolvedAddress() {
        return resolvedAddress;
    }

    public CondicoesAtuais getCurrentConditions() {
        return currentConditions;
    }

    public ArrayList<DiaClima> getDays() {
        return days;
    }

    public DiaClima getDiaAtual() {
        if (days == null || days.isEmpty()) {
            return new DiaClima();
        }

        return days.get(0);
    }
}
